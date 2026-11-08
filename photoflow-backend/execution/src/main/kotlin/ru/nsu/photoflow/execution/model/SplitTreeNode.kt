package ru.nsu.photoflow.execution.model

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

sealed interface SplitTreeNode {
    val isCached: Boolean
    fun iterator(): Iterator<SplitTreeNode>

    data class Leaf(val item: Deferred<Artifact>, val dirty: Boolean = true) : SplitTreeNode {
        override val isCached: Boolean = item.isCompleted && !dirty

        constructor(item: Artifact, dirty: Boolean = true) : this(CompletableDeferred(item), dirty)

        override fun iterator(): Iterator<SplitTreeNode> {
            return generateSequence { this }.iterator()
        }
    }

    data class Branch(val children: List<SplitTreeNode>, val dirty: Boolean = true) : SplitTreeNode {
        override val isCached: Boolean = !dirty

        override fun iterator(): Iterator<SplitTreeNode> {
            return children.iterator()
        }
    }
}