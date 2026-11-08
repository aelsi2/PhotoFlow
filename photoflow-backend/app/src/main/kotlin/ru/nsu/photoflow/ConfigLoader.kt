package ru.nsu.photoflow

import com.akuleshov7.ktoml.Toml
import kotlinx.serialization.decodeFromString
import java.io.File

object ConfigLoader {
    const val USAGE =
        "Usage: [--config=<path to TOML file>] [--host=<host>] " +
            "[--port=<1-65535>] [--database-url=<jdbc url>]"

    private val KNOWN_OPTIONS = setOf("config", "host", "port", "database-url")

    fun load(args: Array<String>): AppConfig {
        val options = parseOptions(args)
        val base = options["config"]?.let(::readFile) ?: AppConfig()
        return base.withOverrides(options)
    }

    private fun parseOptions(args: Array<String>): Map<String, String> {
        val options =
            args.associate { arg ->
                require(arg.startsWith("--") && '=' in arg) { "Invalid argument '$arg', expected --name=value" }
                arg.removePrefix("--").substringBefore('=') to arg.substringAfter('=')
            }

        val unknown = options.keys - KNOWN_OPTIONS
        require(unknown.isEmpty()) { "Unknown option(s): ${unknown.joinToString()}" }
        return options
    }

    private fun readFile(path: String): AppConfig {
        val file = File(path)
        require(file.isFile) { "Config file not found: $path" }

        return try {
            Toml.decodeFromString<AppConfig>(file.readText())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid config file '$path': ${e.message}", e)
        }
    }

    private fun AppConfig.withOverrides(options: Map<String, String>): AppConfig =
        copy(
            server =
                server.copy(
                    host = options["host"] ?: server.host,
                    port = options["port"]?.let(::parsePort) ?: server.port,
                ),
            database = database.copy(url = options["database-url"] ?: database.url),
        )

    private fun parsePort(raw: String): Int =
        requireNotNull(raw.toIntOrNull()) {
            "Port must be an integer, got '$raw'"
        }
}
