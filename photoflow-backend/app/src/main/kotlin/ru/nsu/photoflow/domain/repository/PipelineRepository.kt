package ru.nsu.photoflow.domain.repository

import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.model.PipelineName

interface PipelineRepository {
    fun create(name: PipelineName): PipelineCreationResult

    fun findAll(): List<Pipeline>

    fun findById(id: Int): Pipeline?
}
