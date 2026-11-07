package org.example.app.infrastructure.db

import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.sql.Connection

object DatabaseFactory {
    fun connect(jdbcUrl: String): Database {
        val database =
            Database.connect(
                url = jdbcUrl,
                driver = "org.sqlite.JDBC",
                databaseConfig =
                    DatabaseConfig {
                        defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
                    },
            )

        transaction(database) {
            SchemaUtils.create(PipelinesTable)
        }

        return database
    }
}
