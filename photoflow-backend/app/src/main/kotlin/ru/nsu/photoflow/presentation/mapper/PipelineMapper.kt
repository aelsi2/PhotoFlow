package ru.nsu.photoflow.presentation.mapper

import ru.nsu.photoflow.application.exception.InvalidPipelineException
import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.model.PipelineName
import ru.nsu.photoflow.presentation.dto.CreatePipelineRequest
import ru.nsu.photoflow.presentation.dto.PipelineResponse

fun CreatePipelineRequest.toPipelineName() =
    try {
        PipelineName(name)
    } catch (e: IllegalArgumentException) {
        throw InvalidPipelineException(e.message ?: "Invalid pipeline")
    }

fun Pipeline.toResponse() =
    PipelineResponse(
        id = id,
        name = name.value,
    )
