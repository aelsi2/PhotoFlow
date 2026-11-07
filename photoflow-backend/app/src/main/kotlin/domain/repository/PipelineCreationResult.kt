package org.example.app.domain.repository

import org.example.app.domain.model.Pipeline

sealed interface PipelineCreationResult {
    data class Success(
        val pipeline: Pipeline,
    ) : PipelineCreationResult

    object NameAlreadyInUse : PipelineCreationResult

    data class Failure(
        val throwable: Throwable,
    ) : PipelineCreationResult
}
