package org.example.app.application.service

import org.example.app.application.exception.InvalidPipelineException
import org.example.app.application.exception.PipelineNameAlreadyInUseException
import org.example.app.application.exception.PipelineNotFoundException
import org.example.app.domain.model.Pipeline
import org.example.app.domain.repository.CreatePipelineCommand
import org.example.app.domain.repository.PipelineCreationResult
import org.example.app.domain.repository.PipelineRepository

class PipelineService(
    private val repository: PipelineRepository,
) {
    fun getAll(): List<Pipeline> = repository.findAll()

    fun getById(id: Int): Pipeline = repository.findById(id) ?: throw PipelineNotFoundException(id)

    fun create(command: CreatePipelineCommand): Pipeline {
        validate(command)

        return when (val result = repository.create(command)) {
            is PipelineCreationResult.Success -> result.pipeline
            PipelineCreationResult.NameAlreadyInUse -> throw PipelineNameAlreadyInUseException(command.name)
        }
    }

    private fun validate(command: CreatePipelineCommand) {
        if (command.name.isBlank()) {
            throw InvalidPipelineException("Pipeline name must not be blank")
        }
    }
}
