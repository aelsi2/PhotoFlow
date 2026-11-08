package ru.nsu.photoflow.presentation.mapper

import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.repository.CreatePipelineCommand
import ru.nsu.photoflow.presentation.dto.CreatePipelineRequest
import ru.nsu.photoflow.presentation.dto.PipelineResponse

fun CreatePipelineRequest.toCommand() = CreatePipelineCommand(name = name)

fun Pipeline.toResponse() =
    PipelineResponse(
        id = id,
        name = name,
    )
