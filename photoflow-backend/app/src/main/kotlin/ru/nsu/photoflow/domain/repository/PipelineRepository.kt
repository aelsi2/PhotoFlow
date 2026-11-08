package ru.nsu.photoflow.domain.repository

import ru.nsu.photoflow.domain.model.Pipeline

interface PipelineRepository {
    fun create(command: CreatePipelineCommand): PipelineCreationResult

    fun findAll(): List<Pipeline>

    fun findById(id: Int): Pipeline?
}
