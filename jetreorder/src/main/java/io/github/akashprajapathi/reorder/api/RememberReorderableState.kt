package io.github.akashprajapathi.jetreorder.api

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.akashprajapathi.jetreorder.internal.animation.rememberReorderAnimationController
import io.github.akashprajapathi.jetreorder.internal.animation.rememberReorderAnimationState
import io.github.akashprajapathi.jetreorder.internal.autoscroll.rememberAutoScrollController
import io.github.akashprajapathi.jetreorder.internal.autoscroll.rememberAutoScrollState
import io.github.akashprajapathi.jetreorder.internal.drag.rememberDragController
import io.github.akashprajapathi.jetreorder.internal.drag.rememberDragState
import io.github.akashprajapathi.jetreorder.internal.reorder.rememberReorderController
import io.github.akashprajapathi.jetreorder.internal.reorder.rememberReorderState

@Composable
fun <K> rememberReorderableState(
    listState: LazyListState,
    onMove: (
        from: Int,
        to: Int
    ) -> Unit
): ReorderableState<K> {

    val dragState =
        rememberDragState<K>()

    val dragController =
        rememberDragController(
            dragState
        )

    val autoScrollState =
        rememberAutoScrollState()

    val autoScrollController =
        rememberAutoScrollController(
            lazyListState = listState,
            autoScrollState = autoScrollState,
            dragState = dragState
        )

    val reorderState =
        rememberReorderState<K>()

    val reorderController =
        rememberReorderController(
            reorderState = reorderState,
            dragState = dragState
        )

    val animationState =
        rememberReorderAnimationState<K>()

    val animationController =
        rememberReorderAnimationController(
            dragState = dragState,
            reorderState = reorderState,
            state = animationState
        )

    return remember(
        dragState,
        dragController,
        autoScrollState,
        autoScrollController,
        reorderState,
        reorderController,
        animationState,
        animationController,
        onMove
    ) {

        ReorderableState(
            dragState = dragState,
            autoScrollState = autoScrollState,
            reorderState = reorderState,
            animationState = animationState,
            dragController = dragController,
            autoScrollController = autoScrollController,
            reorderController = reorderController,
            animationController = animationController,
            onMove = onMove
        )
    }
}