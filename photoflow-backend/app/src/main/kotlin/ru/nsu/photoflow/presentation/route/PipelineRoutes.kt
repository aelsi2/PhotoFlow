package ru.nsu.photoflow.presentation.route

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import ru.nsu.photoflow.application.service.PipelineService
import ru.nsu.photoflow.presentation.dto.CreatePipelineRequest
import ru.nsu.photoflow.presentation.mapper.toCommand
import ru.nsu.photoflow.presentation.mapper.toResponse

private const val PIPELINES_PATH = "/api/v1/pipelines"

fun Route.pipelineRoutes(service: PipelineService) {
    route(PIPELINES_PATH) {
        get {
            call.respond(service.getAll().map { it.toResponse() })
        }

        post {
            val request = call.receive<CreatePipelineRequest>()
            val pipeline = service.create(request.toCommand())

            call.response.header(HttpHeaders.Location, "$PIPELINES_PATH/${pipeline.id}")
            call.respond(HttpStatusCode.Created, pipeline.toResponse())
        }

        get("/{id}") {
            val id =
                call.parameters["id"]?.toIntOrNull()
                    ?: throw BadRequestException("Pipeline id must be an integer")

            call.respond(service.getById(id).toResponse())
        }
    }
}
