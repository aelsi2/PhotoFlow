package ru.nsu.photoflow.di

import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.koin.dsl.module
import ru.nsu.photoflow.application.service.PipelineService
import ru.nsu.photoflow.domain.repository.PipelineRepository
import ru.nsu.photoflow.infrastructure.db.PipelinesTable
import ru.nsu.photoflow.infrastructure.repository.SqlitePipelineRepository
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
