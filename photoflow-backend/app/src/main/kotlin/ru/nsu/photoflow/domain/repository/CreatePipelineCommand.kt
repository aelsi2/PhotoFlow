package ru.nsu.photoflow.domain.repository

import ru.nsu.photoflow.domain.model.Pipeline

data class CreatePipelineCommand(
    val name: String,
) {
    fun toPipeline(id: Int) = Pipeline(id = id, name = name)
}
