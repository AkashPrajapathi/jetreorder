package io.github.akashprajapathi.jetreorder.internal.reorder

import io.github.akashprajapathi.jetreorder.internal.drag.DragState
import io.github.akashprajapathi.jetreorder.internal.reorder.model.ItemLayout

internal class ReorderController<K>(
    private val reorderState: ReorderState<K>,
    private val dragState: DragState<K>
) {

    internal fun updateItemLayouts(
        layouts: List<ItemLayout<K>>
    ) {

        reorderState.itemLayouts = layouts

        if (dragState.isDragging) {
            resolveTarget()
        }
    }

    internal fun startDrag() {

        reorderState.targetIndex =
            dragState.sourceIndex

        reorderState.targetKey =
            dragState.sourceKey
    }

    internal fun resolveTarget() {

        if (!dragState.isDragging) {
            return
        }

        val pointerY =
            dragState.pointerY

        val layouts =
            reorderState.itemLayouts

        if (layouts.isEmpty()) {
            return
        }

        val targetItem =
            layouts.firstOrNull {

                pointerY >= it.start &&
                        pointerY < it.end
            }

        val resolvedTarget = when {

            targetItem != null ->
                targetItem

            pointerY < layouts.first().start ->
                layouts.first()

            pointerY >= layouts.last().end ->
                layouts.last()

            else ->
                null
        }

        reorderState.targetIndex =
            resolvedTarget?.index
                ?: dragState.sourceIndex

        reorderState.targetKey =
            resolvedTarget?.key
                ?: dragState.sourceKey
    }

    internal fun endDrag() {

        reorderState.targetIndex = -1

        reorderState.targetKey = null
    }
}