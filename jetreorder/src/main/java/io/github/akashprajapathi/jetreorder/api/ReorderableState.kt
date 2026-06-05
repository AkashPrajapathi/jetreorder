package io.github.akashprajapathi.jetreorder.api

import io.github.akashprajapathi.jetreorder.internal.animation.ReorderAnimationController
import io.github.akashprajapathi.jetreorder.internal.autoscroll.AutoScrollController
import io.github.akashprajapathi.jetreorder.internal.autoscroll.AutoScrollState
import io.github.akashprajapathi.jetreorder.internal.drag.DragController
import io.github.akashprajapathi.jetreorder.internal.drag.DragState
import io.github.akashprajapathi.jetreorder.internal.reorder.ReorderController
import io.github.akashprajapathi.jetreorder.internal.reorder.ReorderState
import io.github.akashprajapathi.jetreorder.internal.reorder.model.ItemLayout

class ReorderableState<K> internal constructor(

    private val dragState: DragState<K>,

    private val autoScrollState: AutoScrollState,

    private val reorderState: ReorderState<K>,

    private val dragController: DragController<K>,

    private val autoScrollController: AutoScrollController<K>,

    private val reorderController: ReorderController<K>,

    private val animationController: ReorderAnimationController<K>,

    private val onMove: (
        from: Int,
        to: Int
    ) -> Unit
) {

    internal val isDragging: Boolean
        get() = dragState.isDragging

    internal val overlayOffsetY: Float
        get() = pointerY - grabOffsetY

    internal var overlayHeight: Float
        get() = dragState.overlayHeight
        set(value) {
            dragState.overlayHeight = value
        }

    internal val visualOverlayOffsetY: Float
        get() = overlayOffsetY.coerceIn(
            0f,
            (containerHeight - overlayHeight)
                .coerceAtLeast(0f)
        )

    internal val sourceKey: K?
        get() = dragState.sourceKey

    internal val pointerY: Float
        get() = dragState.pointerY

    internal val grabOffsetY: Float
        get() = dragState.grabOffsetY

    internal val targetIndex: Int
        get() = reorderState.targetIndex

    internal var containerHeight: Float
        get() = autoScrollState.containerHeight
        set(value) {
            autoScrollState.containerHeight = value
        }

    internal fun startDrag(
        sourceKey: K,
        sourceIndex: Int,
        pointerY: Float,
        grabOffsetY: Float
    ) {

        dragController.startDrag(
            sourceKey = sourceKey,
            sourceIndex = sourceIndex,
            pointerY = pointerY,
            grabOffsetY = grabOffsetY
        )

        reorderController.startDrag()

        autoScrollController.start()
    }

    internal fun updatePointerY(
        pointerY: Float
    ) {

        dragController.updatePointerY(
            pointerY
        )

        reorderController.resolveTarget()
    }

    internal fun endDrag() {

        autoScrollController.stop()

        reorderController.endDrag()

        dragController.endDrag()

        animationController.clearOffsets()
    }

    internal fun updateItemLayouts(
        layouts: List<ItemLayout<K>>
    ) {

        reorderController.updateItemLayouts(
            layouts
        )
    }

    internal fun updateAnimationOffsets() {

        animationController.updateOffsets()
    }

    internal fun getItemOffset(
        key: K
    ): Float {

        return animationController.getOffset(
            key
        )
    }

    internal fun performMoveIfNeeded() {

        val sourceIndex =
            dragState.sourceIndex

        val targetIndex =
            reorderState.targetIndex

        if (
            sourceIndex >= 0 &&
            targetIndex >= 0 &&
            sourceIndex != targetIndex
        ) {

            onMove(
                sourceIndex,
                targetIndex
            )
        }
    }

    internal fun findItemAt(
        y: Float
    ): ItemLayout<K>? {

        return reorderState.itemLayouts
            .firstOrNull {

                y >= it.start &&
                        y < it.end
            }
    }
}