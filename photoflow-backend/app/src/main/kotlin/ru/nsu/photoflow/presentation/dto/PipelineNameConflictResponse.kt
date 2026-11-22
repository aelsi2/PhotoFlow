package ru.nsu.photoflow.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class PipelineNameConflictResponse(
    val message: String,
    val existingPipelineId: Int,
)
