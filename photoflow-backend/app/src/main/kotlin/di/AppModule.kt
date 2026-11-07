package org.example.app.di

import org.example.app.application.service.PipelineService
import org.example.app.domain.repository.PipelineRepository
import org.example.app.infrastructure.db.DatabaseFactory
import org.example.app.infrastructure.repository.SqlitePipelineRepository
import org.koin.dsl.module

fun appModule(databaseUrl: String) =
    module {
        single(createdAtStart = true) { DatabaseFactory.connect(databaseUrl) }
        single<PipelineRepository> { SqlitePipelineRepository(get()) }
        single { PipelineService(get()) }
    }
