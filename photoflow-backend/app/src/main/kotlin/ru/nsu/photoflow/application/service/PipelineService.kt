package ru.nsu.photoflow.application.service

import ru.nsu.photoflow.application.exception.PipelineNameAlreadyInUseException
import ru.nsu.photoflow.application.exception.PipelineNotFoundException
import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.model.PipelineName
import ru.nsu.photoflow.domain.repository.PipelineCreationResult
import ru.nsu.photoflow.domain.repository.PipelineRepository

class PipelineService(
    private val repository: PipelineRepository,
) {
    fun getAll(): List<Pipeline> = repository.findAll()

    fun getById(id: Int): Pipeline = repository.findById(id) ?: throw PipelineNotFoundException(id)

    fun create(name: PipelineName): Pipeline =
        when (val result = repository.create(name)) {
            is PipelineCreationResult.Success -> result.pipeline
            PipelineCreationResult.NameAlreadyInUse ->
                throw PipelineNameAlreadyInUseException(name.value)
        }
}
