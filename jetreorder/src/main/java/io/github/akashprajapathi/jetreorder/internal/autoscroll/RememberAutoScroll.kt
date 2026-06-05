package io.github.akashprajapathi.jetreorder.internal.autoscroll

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.github.akashprajapathi.jetreorder.internal.drag.DragState

@Composable
internal fun rememberAutoScrollState(): AutoScrollState {

    return remember {
        AutoScrollState()
    }
}

@Composable
internal fun <K> rememberAutoScrollController(
    lazyListState: LazyListState,
    autoScrollState: AutoScrollState,
    dragState: DragState<K>,
    config: AutoScrollConfig = AutoScrollConfig()
): AutoScrollController<K> {

    val scope =
        rememberCoroutineScope()

    return remember(
        lazyListState,
        autoScrollState,
        dragState,
        scope,
        config
    ) {

        AutoScrollController<K>(
            lazyListState = lazyListState,
            autoScrollState = autoScrollState,
            dragState = dragState,
            scope = scope,
            config = config
        )
    }
}