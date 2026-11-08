package ru.nsu.photoflow.type_system

/**
 * A file format with a set of known file extensions.
 *
 * @property formatName A short name of the format displayed to users, e.g. "JPEG".
 * @property formatDescription A longer description of the format displayed to users, e.g. "JPEG images".
 * @property allowedFileNameSuffixes A list of file extension suffixes associated with this format
 * (excluding the leading period), e.g. "jpg", "jpeg".
 */
data class SimpleType(
    val formatName: String,
    val formatDescription: String,
    val allowedFileNameSuffixes: List<String>
) : ItemType {
    /**
     * Checks if a file name matches the current type.
     */
    fun match(name: String): Boolean = allowedFileNameSuffixes.any { name.endsWith(".$it") }

    override fun isSuper(type: ComplexType): Boolean {
        return type is SimpleType && allowedFileNameSuffixes.containsAll(type.allowedFileNameSuffixes)
    }
}