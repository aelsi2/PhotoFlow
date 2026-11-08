package ru.nsu.photoflow.application.service

import ru.nsu.photoflow.application.exception.InvalidPipelineException
import ru.nsu.photoflow.application.exception.PipelineNameAlreadyInUseException
import ru.nsu.photoflow.application.exception.PipelineNotFoundException
import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.repository.CreatePipelineCommand
import ru.nsu.photoflow.domain.repository.PipelineCreationResult
import ru.nsu.photoflow.domain.repository.PipelineRepository

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
