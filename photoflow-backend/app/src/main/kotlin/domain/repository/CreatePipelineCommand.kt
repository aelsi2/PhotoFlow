package org.example.app.domain.repository

import org.example.app.domain.model.Pipeline

data class CreatePipelineCommand(
    val name: String,
) {
    fun toPipeline(id: Int) = Pipeline(id = id, name = name)
}
