package ru.nsu.photoflow.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class PipelineResponse(
    val id: Int,
    val name: String,
)
