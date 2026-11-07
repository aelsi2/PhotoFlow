package org.example.app.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreatePipelineRequest(
    val name: String,
)
