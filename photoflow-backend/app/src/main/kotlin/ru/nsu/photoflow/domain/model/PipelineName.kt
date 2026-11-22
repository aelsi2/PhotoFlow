package ru.nsu.photoflow.domain.model

@JvmInline
value class PipelineName(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Pipeline name must not be blank" }
    }
}
