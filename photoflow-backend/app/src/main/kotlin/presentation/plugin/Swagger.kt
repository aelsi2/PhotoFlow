package org.example.app.presentation.plugin

import io.ktor.server.application.Application
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureSwagger() {
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
    }
}
