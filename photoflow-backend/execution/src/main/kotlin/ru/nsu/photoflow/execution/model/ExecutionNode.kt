package ru.nsu.photoflow.execution.model

import ru.nsu.photoflow.execution.ExecutionContext

/**
 * Represents a single block in the execution model.
 */
interface ExecutionNode {
    /**
     * Runs the action on the given inputs. The action should use [ru.nsu.photoflow.execution.CacheManager] from [context]
     * with the given [cacheKey] to create output files so that they are cached between runs. Stderr or any other logs
     * should be written to the stream from [context].
     */
    fun run(inputs: List<SplitTreeNode>, cacheKey: CacheKey, context: ExecutionContext): List<SplitTreeNode>
}