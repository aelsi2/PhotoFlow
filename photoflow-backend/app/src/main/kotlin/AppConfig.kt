package org.example.app

import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
    val server: ServerConfig = ServerConfig(),
    val database: DbConfig = DbConfig(),
)

@Serializable
data class ServerConfig(
    val host: String = "0.0.0.0",
    val port: Int = 8080,
) {
    init {
        require(port in 1..65535) { "Port must be in 1..65535, got $port" }
    }
}

@Serializable
data class DbConfig(
    val url: String = "jdbc:sqlite:pipelines.db",
)
