package org.example.app.presentation.plugin

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.example.app.application.service.PipelineService
import org.example.app.presentation.route.pipelineRoutes
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val service by inject<PipelineService>()

    routing {
        pipelineRoutes(service)
    }
}
