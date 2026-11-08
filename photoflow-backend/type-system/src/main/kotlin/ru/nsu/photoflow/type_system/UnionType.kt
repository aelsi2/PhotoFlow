package ru.nsu.photoflow.type_system

/**
 * A union of [ItemType]s (e.g. union image = PNG OR JPEG OR RAW).
 */
data class UnionType(val types: List<ItemType>) : ComplexType {
    override fun isSuper(type: ComplexType): Boolean {
        if (types.any { it.isSuper(type) }) {
            return true
        }
        return type is UnionType && type.types.all { otherType -> types.any { it.isSuper(otherType) } }
    }
}