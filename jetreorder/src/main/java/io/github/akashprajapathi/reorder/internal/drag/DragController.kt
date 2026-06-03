package io.github.akashprajapathi.jetreorder.internal.drag

internal class DragController<K>(
    private val state: DragState<K>
) {

    internal fun startDrag(
        sourceKey: K,
        sourceIndex: Int,
        pointerY: Float,
        grabOffsetY: Float
    ) {

        state.isDragging = true

        state.sourceKey = sourceKey

        state.sourceIndex = sourceIndex

        state.pointerY = pointerY

        state.grabOffsetY = grabOffsetY
    }

    internal fun updatePointerY(
        pointerY: Float
    ) {

        state.pointerY = pointerY
    }

    internal fun endDrag() {

        state.isDragging = false

        state.sourceKey = null

        state.sourceIndex = -1

        state.pointerY = 0f

        state.grabOffsetY = 0f
    }
}