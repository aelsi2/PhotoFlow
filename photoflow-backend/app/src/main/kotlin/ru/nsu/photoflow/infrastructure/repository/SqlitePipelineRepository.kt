package ru.nsu.photoflow.infrastructure.repository

import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.exceptions.ExposedSQLException
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.sqlite.SQLiteErrorCode
import org.sqlite.SQLiteException
import ru.nsu.photoflow.domain.model.Pipeline
import ru.nsu.photoflow.domain.model.PipelineName
import ru.nsu.photoflow.domain.repository.PipelineCreationResult
import ru.nsu.photoflow.domain.repository.PipelineRepository
import ru.nsu.photoflow.infrastructure.db.PipelinesTable

class SqlitePipelineRepository(
    private val database: Database,
) : PipelineRepository {
    override fun create(name: PipelineName): PipelineCreationResult =
        try {
            transaction(database) {
                val insertedId =
                    PipelinesTable.insert {
                        it[PipelinesTable.name] = name.value
                    } get PipelinesTable.id

                PipelineCreationResult.Success(
                    pipeline = Pipeline(id = insertedId.value, name = name),
                )
            }
        } catch (e: ExposedSQLException) {
            if (!e.isUniqueViolation()) {
                throw e
            }
            val existingId =
                transaction(database) {
                    PipelinesTable
                        .selectAll()
                        .where { PipelinesTable.name eq name.value }
                        .firstOrNull()
                        ?.get(PipelinesTable.id)
                        ?.value
                } ?: throw e
            PipelineCreationResult.NameAlreadyInUse(existingPipelineId = existingId)
        }

    override fun findAll(): List<Pipeline> =
        transaction(database) {
            PipelinesTable.selectAll().map { it.toPipeline() }
        }

    override fun findById(id: Int): Pipeline? =
        transaction(database) {
            PipelinesTable
                .selectAll()
                .where { PipelinesTable.id eq id }
                .firstOrNull()
                ?.toPipeline()
        }
}

private fun ResultRow.toPipeline() =
    Pipeline(
        id = this[PipelinesTable.id].value,
        name = PipelineName(this[PipelinesTable.name]),
    )

private fun ExposedSQLException.isUniqueViolation(): Boolean =
    (cause as? SQLiteException)?.resultCode == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE
