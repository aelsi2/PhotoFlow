package ru.nsu.photoflow.type_system

/**
 * A type describing a list of items, which themselves are composed of multiple items.
 *
 * @property element The element type, which every child item must match.
 */
data class ListType(val element: ComplexType) : ItemType {
    override fun isSuper(type: ComplexType): Boolean {
        return type is ListType && element.isSuper(type.element)
    }
}