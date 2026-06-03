package io.github.akashprajapathi.jetreorder.internal.animation

import io.github.akashprajapathi.jetreorder.internal.drag.DragState
import io.github.akashprajapathi.jetreorder.internal.reorder.ReorderState

internal class ReorderAnimationController<K>(
    private val dragState: DragState<K>,
    private val reorderState: ReorderState<K>,
    private val state: ReorderAnimationState<K>
) {

    internal fun updateOffsets() {

        state.offsets.clear()

        if (!dragState.isDragging) {
            return
        }

        val sourceIndex =
            dragState.sourceIndex

        val targetIndex =
            reorderState.targetIndex

        when {

            targetIndex > sourceIndex -> {

                reorderState.itemLayouts.forEach { layout ->

                    if (
                        layout.index in
                        (sourceIndex + 1)..targetIndex
                    ) {

                        val itemHeight =
                            layout.end - layout.start

                        state.offsets[layout.key] =
                            -itemHeight
                    }
                }
            }

            targetIndex < sourceIndex -> {

                reorderState.itemLayouts.forEach { layout ->

                    if (
                        layout.index in
                        targetIndex until sourceIndex
                    ) {

                        val itemHeight =
                            layout.end - layout.start

                        state.offsets[layout.key] =
                            itemHeight
                    }
                }
            }
        }
    }

    internal fun getOffset(
        key: K
    ): Float {

        return state.offsets[key]
            ?: 0f
    }

    internal fun clearOffsets() {

        state.offsets.clear()
    }
}