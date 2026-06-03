package io.github.akashprajapathi.jetreorder.internal.reorder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.akashprajapathi.jetreorder.internal.drag.DragState

@Composable
internal fun <K> rememberReorderState(): ReorderState<K> {

    return remember {
        ReorderState<K>()
    }
}

@Composable
internal fun <K> rememberReorderController(
    reorderState: ReorderState<K>,
    dragState: DragState<K>
): ReorderController<K> {

    return remember(
        reorderState,
        dragState
    ) {

        ReorderController<K>(
            reorderState = reorderState,
            dragState = dragState
        )
    }
}