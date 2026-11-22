package ru.nsu.photoflow

import com.akuleshov7.ktoml.Toml
import kotlinx.serialization.decodeFromString
import java.io.File

object ConfigLoader {
    const val USAGE =
        "Usage: [--config=<path to TOML file>] [--host=<host>] " +
            "[--port=<1-65535>] [--database-url=<jdbc url>]"

    fun load(args: Array<String>): AppConfig {
        var configPath: String? = null
        var host: String? = null
        var port: String? = null
        var databaseUrl: String? = null

        for (arg in args) {
            require(arg.startsWith("--") && '=' in arg) {
                "Invalid argument '$arg', expected --name=value"
            }
            val name = arg.removePrefix("--").substringBefore('=')
            val value = arg.substringAfter('=')
            require(value.isNotEmpty()) { "Missing value for --$name" }

            when (name) {
                "config" -> configPath = value
                "host" -> host = value
                "port" -> port = value
                "database-url" -> databaseUrl = value
                else -> throw IllegalArgumentException("Unknown option: --$name")
            }
        }

        val base = configPath?.let(::readFile) ?: AppConfig()
        return base.copy(
            server =
                base.server.copy(
                    host = host ?: base.server.host,
                    port = port?.let(::parsePort) ?: base.server.port,
                ),
            database = base.database.copy(url = databaseUrl ?: base.database.url),
        )
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

    private fun parsePort(raw: String): Int =
        requireNotNull(raw.toIntOrNull()) {
            "Port must be an integer, got '$raw'"
        }
}
