package ru.nsu.photoflow.presentation.plugin

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import ru.nsu.photoflow.application.service.PipelineService
import ru.nsu.photoflow.presentation.route.pipelineRoutes

fun Application.configureRouting() {
    val service by inject<PipelineService>()

    routing {
        pipelineRoutes(service)
    }
}
