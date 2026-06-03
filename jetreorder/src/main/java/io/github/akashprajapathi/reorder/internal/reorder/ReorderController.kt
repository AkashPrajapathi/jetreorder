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

        val targetItem =
            reorderState.itemLayouts
                .firstOrNull {

                    pointerY >= it.start &&
                            pointerY < it.end
                }

        reorderState.targetIndex =
            targetItem?.index
                ?: dragState.sourceIndex

        reorderState.targetKey =
            targetItem?.key
                ?: dragState.sourceKey
    }

    internal fun endDrag() {

        reorderState.targetIndex = -1

        reorderState.targetKey = null
    }
}