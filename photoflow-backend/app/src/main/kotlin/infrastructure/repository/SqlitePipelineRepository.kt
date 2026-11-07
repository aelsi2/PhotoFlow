package org.example.app.infrastructure.repository

import org.example.app.domain.model.Pipeline
import org.example.app.domain.repository.CreatePipelineCommand
import org.example.app.domain.repository.PipelineCreationResult
import org.example.app.domain.repository.PipelineRepository
import org.example.app.infrastructure.db.PipelinesTable
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.exceptions.ExposedSQLException
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.sqlite.SQLiteErrorCode
import org.sqlite.SQLiteException

class SqlitePipelineRepository(
    private val database: Database,
) : PipelineRepository {
    override fun create(command: CreatePipelineCommand): PipelineCreationResult =
        try {
            transaction(database) {
                val insertedId =
                    PipelinesTable.insert {
                        it[name] = command.name
                    } get PipelinesTable.id

                PipelineCreationResult.Success(pipeline = command.toPipeline(id = insertedId.value))
            }
        } catch (e: ExposedSQLException) {
            if (e.isUniqueViolation()) {
                PipelineCreationResult.NameAlreadyInUse
            } else {
                PipelineCreationResult.Failure(throwable = e)
            }
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
        name = this[PipelinesTable.name],
    )

private fun ExposedSQLException.isUniqueViolation(): Boolean =
    (cause as? SQLiteException)?.resultCode == SQLiteErrorCode.SQLITE_CONSTRAINT_UNIQUE
