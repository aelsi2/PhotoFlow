package org.example.app.application.exception

sealed class PipelineException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

class PipelineNotFoundException(
    id: Int,
) : PipelineException("Pipeline with id=$id not found")

class PipelineNameAlreadyInUseException(
    name: String,
) : PipelineException("Pipeline name '$name' is already in use")

class InvalidPipelineException(
    message: String,
) : PipelineException(message)

class PipelineStorageException(
    cause: Throwable,
) : PipelineException("Pipeline storage failure", cause)
