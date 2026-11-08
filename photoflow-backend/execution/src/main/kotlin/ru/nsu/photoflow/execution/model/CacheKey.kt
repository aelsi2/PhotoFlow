package ru.nsu.photoflow.execution.model

data class CacheKey(
    private val nodeId: UInt,
    private val outputPath: List<UInt> = listOf(),
    private val outputIndex: UInt = 0U,
) {
    override fun toString(): String {
        return "n$nodeId-o$outputIndex-p${outputPath.joinToString(".") { it.toString() }}"
    }

    fun withOutputIndex(newIndex: UInt) = CacheKey(nodeId, outputPath, newIndex)
    fun withNextPath(nextPath: UInt) = CacheKey(nodeId, outputPath + nextPath, outputIndex)
}
