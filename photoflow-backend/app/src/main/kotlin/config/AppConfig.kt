package org.example.app.config

data class AppConfig(
    val host: String,
    val port: Int,
    val databaseUrl: String,
) {
    companion object {
        private const val DEFAULT_HOST = "0.0.0.0"
        private const val DEFAULT_PORT = 8080
        private const val DEFAULT_DATABASE_URL = "jdbc:sqlite:pipelines.db"
        private val PORT_RANGE = 1..65535

        fun fromEnv(env: Map<String, String> = System.getenv()): AppConfig =
            AppConfig(
                host = env["PHOTOFLOW_HOST"] ?: DEFAULT_HOST,
                port = parsePort(env["PHOTOFLOW_PORT"]),
                databaseUrl = env["PHOTOFLOW_DATABASE_URL"] ?: DEFAULT_DATABASE_URL,
            )

        private fun parsePort(raw: String?): Int {
            if (raw == null) return DEFAULT_PORT
            val port = raw.toIntOrNull()
            require(port != null && port in PORT_RANGE) {
                "PHOTOFLOW_PORT must be an integer in $PORT_RANGE, got '$raw'"
            }
            return port
        }
    }
}
