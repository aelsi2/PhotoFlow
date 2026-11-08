package ru.nsu.photoflow.execution.model

import ru.nsu.photoflow.type_system.ItemType
import ru.nsu.photoflow.type_system.ListType
import ru.nsu.photoflow.type_system.SimpleType
import java.nio.file.Path

/**
 * An item passed as data between blocks during execution. Can either be a [File] or a [List].
 */
sealed interface Artifact {
    /**
     * The type of the item.
     */
    val type: ItemType

    /**
     * An item that represents a single file.
     */
    data class File(
        val file: Path, override val type: SimpleType
    ) : Artifact

    /**
     * An item that represents a list of files or other lists.
     */
    data class List(
        val children: kotlin.collections.List<Artifact>, override val type: ListType
    ) : Artifact
}
