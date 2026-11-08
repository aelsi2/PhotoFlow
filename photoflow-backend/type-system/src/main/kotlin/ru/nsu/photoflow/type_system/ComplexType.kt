package ru.nsu.photoflow.type_system

/**
 * An abstract item type, which may include multiple concrete [ItemType]s. Use `is` and `as?` to downcast.
 */
sealed interface ComplexType {
    /**
     * Checks if the current [ComplexType] is a supertype of the specified [ComplexType].
     */
    fun isSuper(type: ComplexType): Boolean
}