package ru.nsu.photoflow.domain.repository

import ru.nsu.photoflow.domain.model.Pipeline

sealed interface PipelineCreationResult {
    data class Success(
        val pipeline: Pipeline,
    ) : PipelineCreationResult

    data class NameAlreadyInUse(
        val existingPipelineId: Int,
    ) : PipelineCreationResult
}
