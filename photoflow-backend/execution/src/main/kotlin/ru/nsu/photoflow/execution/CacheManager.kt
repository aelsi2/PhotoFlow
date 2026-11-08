package ru.nsu.photoflow.execution

import ru.nsu.photoflow.execution.model.CacheKey
import ru.nsu.photoflow.execution.model.Artifact
import ru.nsu.photoflow.type_system.ListType
import ru.nsu.photoflow.type_system.SimpleType

/**
 * A service responsible for saving files produced by [ru.nsu.photoflow.execution.model.ExecutionNode]s
 * between pipeline runs.
 */
interface CacheManager {
    /**
     * Creates or updates an existing file identified by the specified [cacheKey].
     * The type of the returned [Artifact.File] is specified by [type].
     */
    fun updateFile(cacheKey: CacheKey, type: SimpleType): Artifact.File

    /**
     * Creates or updates an existing list identified by the specified [cacheKey].
     * The type of the returned [Artifact.List] is specified by [type].
     */
    fun updateList(cacheKey: CacheKey, type: ListType): Artifact.List

    /**
     * Checks if the cache manager has an item associated with the specified [cacheKey].
     */
    fun isCached(cacheKey: CacheKey)

    /**
     * Gets a cached item with the specified [cacheKey]. Returns `null` if the key is not associated with an item.
     */
    fun getCached(cacheKey: CacheKey): Artifact?
}