package io.github.akashprajapathi.jetreorder.internal.drag

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
internal fun <K> rememberDragState(): DragState<K> {

    return remember {
        DragState<K>()
    }
}

@Composable
internal fun <K> rememberDragController(
    state: DragState<K>
): DragController<K> {

    return remember(state) {
        DragController<K>(state)
    }
}