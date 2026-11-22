package ru.nsu.photoflow

import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
class Printer(
    val message: String,
) {
    @OptIn(ExperimentalTime::class)
    fun printMessage() =
        runBlocking {
            val now: Instant = Clock.System.now()
            launch {
                delay(1000L)
                println(now.toString())
            }
            println(message)
        }
}
