package org.example.app.infrastructure.db

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object PipelinesTable : IntIdTable("pipelines") {
    val name = text("name").uniqueIndex()
}
