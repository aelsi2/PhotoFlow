package org.example.app.presentation.mapper

import org.example.app.domain.model.Pipeline
import org.example.app.domain.repository.CreatePipelineCommand
import org.example.app.presentation.dto.CreatePipelineRequest
import org.example.app.presentation.dto.PipelineResponse

fun CreatePipelineRequest.toCommand() = CreatePipelineCommand(name = name)

fun Pipeline.toResponse() =
    PipelineResponse(
        id = id,
        name = name,
    )
