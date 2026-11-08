package ru.nsu.photoflow.execution

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import java.io.OutputStream

data class ExecutionContext(
    val logStream: OutputStream,
    val cacheManager: CacheManager,
    val coroutineScope: CoroutineScope,
    val processDispatcher: CoroutineDispatcher
)