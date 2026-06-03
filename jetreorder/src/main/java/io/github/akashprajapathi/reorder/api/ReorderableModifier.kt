package io.github.akashprajapathi.jetreorder.api

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun <K> Modifier.reorderable(
    state: ReorderableState<K>
): Modifier {

    return pointerInput(state) {

        detectDragGesturesAfterLongPress(

            onDragStart = { offset ->

                val sourceLayout =
                    state.findItemAt(
                        y = offset.y
                    )
                        ?: return@detectDragGesturesAfterLongPress

                val grabOffsetY =
                    offset.y - sourceLayout.start

                state.startDrag(
                    sourceKey = sourceLayout.key,
                    sourceIndex = sourceLayout.index,
                    pointerY = offset.y,
                    grabOffsetY = grabOffsetY
                )
            },

            onDragEnd = {

                state.performMoveIfNeeded()

                state.endDrag()
            },

            onDragCancel = {

                state.endDrag()
            }

        ) { change, _ ->

            state.updatePointerY(
                change.position.y
            )
        }
    }
}