package io.github.akashprajapathi.jetreorder.internal.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.akashprajapathi.jetreorder.internal.drag.DragState
import io.github.akashprajapathi.jetreorder.internal.reorder.ReorderState

@Composable
internal fun <K> rememberReorderAnimationState():
        ReorderAnimationState<K> {

    return remember {
        ReorderAnimationState()
    }
}

@Composable
internal fun <K> rememberReorderAnimationController(
    dragState: DragState<K>,
    reorderState: ReorderState<K>,
    state: ReorderAnimationState<K>
): ReorderAnimationController<K> {

    return remember(
        dragState,
        reorderState,
        state
    ) {

        ReorderAnimationController<K>(
            dragState = dragState,
            reorderState = reorderState,
            state = state
        )
    }
}