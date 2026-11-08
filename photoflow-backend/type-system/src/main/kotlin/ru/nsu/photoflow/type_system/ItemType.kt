package ru.nsu.photoflow.type_system

/**
 * A concrete type an item may have. Use `is` and `as?` to downcast.
 */
sealed interface ItemType: ComplexType {
}