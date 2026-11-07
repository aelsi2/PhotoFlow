package org.example.app.domain.repository

import org.example.app.domain.model.Pipeline

interface PipelineRepository {
    fun create(command: CreatePipelineCommand): PipelineCreationResult

    fun findAll(): List<Pipeline>

    fun findById(id: Int): Pipeline?
}
