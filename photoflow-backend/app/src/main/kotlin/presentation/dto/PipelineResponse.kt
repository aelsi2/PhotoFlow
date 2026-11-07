package org.example.app.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class PipelineResponse(
    val id: Int,
    val name: String,
)
