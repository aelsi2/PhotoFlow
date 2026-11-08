package ru.nsu.photoflow.execution.model

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ru.nsu.photoflow.execution.ExecutionContext

abstract class SimpleNode : ExecutionNode {
    /**
     * Gets the number of outputs produced by [run] when provided with the specified number of inputs.
     */
    abstract fun getOutputCount(inputCount: Int): Int

    /**
     * Processes a list of input items to produce a list of output items.
     * Each input/output item corresponds to an input/output port in the same order.
     */
    abstract suspend fun run(inputs: List<Artifact>): List<Artifact>

    /**
     * Splits the trees passed in [inputs], tries to match them with each other, forming flat lists of [Artifact]s,
     * repeating leaves, if needed. Runs the action specified by the other overload of [run] for each input set,
     * forming the same structure as the most nested input tree for each output.
     *
     * For example:
     * ```txt
     *                                 |-------------|
     *  Leaf1 |                        |   Action    |                        | run([Leaf1, Leaf5, Leaf7])[0]
     *  Leaf2 | Branch |               |             |               | Branch | run([Leaf2, Leaf5, Leaf7])[0]
     *                 | Branch | ===> o in1    out1 o ===> | Branch |
     *  Leaf3 | Branch |               |             |               | Branch | run([Leaf3, Leaf6, Leaf7])[0]
     *  Leaf4 |                        |             |                        | run([Leaf4, Leaf6, Leaf7])[0]
     *                                 |             |
     *                                 |             |                        | run([Leaf1, Leaf5, Leaf7])[1]
     *           Leaf5 |               |             |               | Branch | run([Leaf2, Leaf5, Leaf7])[1]
     *                 | Branch | ===> o in2    out2 o ===> | Branch |
     *           Leaf6 |               |             |               | Branch | run([Leaf3, Leaf6, Leaf7])[1]
     *                                 |             |                        | run([Leaf4, Leaf6, Leaf7])[1]
     *                    Leaf7 | ===> o in3         |
     *                                 |             |
     *                                 |-------------|
     * ```
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun run(
        inputs: List<SplitTreeNode>,
        cacheKey: CacheKey,
        context: ExecutionContext
    ): List<SplitTreeNode> {
        // If all inputs are leaves, run the action once
        if (inputs.all { it is SplitTreeNode.Leaf }) {
            if (inputs.all { !(it as SplitTreeNode.Leaf).dirty }) {
                val inputs = inputs.map { (it as SplitTreeNode.Leaf).item.getCompleted() }
            }
            val outputs = context.coroutineScope.async {
                val inputs = inputs.map { (it as SplitTreeNode.Leaf).item.await() }
                return@async withContext(context.processDispatcher) {
                    run(inputs)
                }
            }
            val splitOutputs = List(getOutputCount(inputs.size)) {
                context.coroutineScope.async {
                    val outputList = outputs.await()
                    outputList[it]
                }
            }
            return splitOutputs.map { SplitTreeNode.Leaf(it) }
        }

        // Each input is iterated simultaneously.
        val iterators = inputs.map { it.iterator() }.toList()
        val elementInputs = generateSequence {
            if (!iterators.all { it.hasNext() }) {
                iterators.map { it.next() }
            } else {
                null
            }
        }

        // Each element of the outer list corresponds to an output.
        // Each element of the inner lists corresponds to a value produced by one iteration.
        val results = mutableListOf<MutableList<SplitTreeNode>>()
        for ((index, inputs) in elementInputs.withIndex()) {
            val currentResults = run(inputs, cacheKey.withNextPath(index.toUInt()), context)
            while (results.size < currentResults.size) {
                results.add(mutableListOf())
            }
            results.zip(currentResults).forEach { (result, current) -> result.add(current) }
        }
        return results.map { SplitTreeNode.Branch(it) }
    }
}