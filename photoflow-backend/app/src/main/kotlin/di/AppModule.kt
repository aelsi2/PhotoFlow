package org.example.app.di

import org.example.app.application.service.PipelineService
import org.example.app.domain.repository.PipelineRepository
import org.example.app.infrastructure.db.PipelinesTable
import org.example.app.infrastructure.repository.SqlitePipelineRepository
import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.koin.dsl.module
import java.sql.Connection

fun appModule(databaseUrl: String) =
    module {
        single(createdAtStart = true) {
            Database
                .connect(
                    url = databaseUrl,
                    driver = "org.sqlite.JDBC",
                    databaseConfig = DatabaseConfig { defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE },
                ).also { db ->
                    transaction(db) { SchemaUtils.create(PipelinesTable) }
                }
        }
        single<PipelineRepository> { SqlitePipelineRepository(get()) }
        single { PipelineService(get()) }
    }
