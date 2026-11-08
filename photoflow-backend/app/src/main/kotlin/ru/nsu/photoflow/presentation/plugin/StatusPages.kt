package ru.nsu.photoflow.presentation.plugin

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import ru.nsu.photoflow.application.exception.InvalidPipelineException
import ru.nsu.photoflow.application.exception.PipelineNameAlreadyInUseException
import ru.nsu.photoflow.application.exception.PipelineNotFoundException
import ru.nsu.photoflow.presentation.dto.ErrorResponse

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<InvalidPipelineException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, ErrorResponse(cause.message.orEmpty()))
        }
        exception<BadRequestException> { call, _ ->
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid request"))
        }
        exception<PipelineNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, ErrorResponse(cause.message.orEmpty()))
        }
        exception<PipelineNameAlreadyInUseException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, ErrorResponse(cause.message.orEmpty()))
        }
        exception<Throwable> { call, cause ->
            call.application.log.error("Unhandled exception", cause)
            call.respond(HttpStatusCode.InternalServerError, ErrorResponse("Internal server error"))
        }
    }
}
