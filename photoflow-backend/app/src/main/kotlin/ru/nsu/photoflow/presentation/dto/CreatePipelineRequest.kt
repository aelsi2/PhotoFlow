package ru.nsu.photoflow.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreatePipelineRequest(
    val name: String,
)
