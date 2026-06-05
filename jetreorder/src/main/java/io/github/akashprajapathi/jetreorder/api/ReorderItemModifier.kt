package io.github.akashprajapathi.jetreorder.api

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun <K> Modifier.reorderItem(
    key: K,
    state: ReorderableState<K>
): Modifier = composed {

    val translationY by animateFloatAsState(
        targetValue = state.getItemOffset(key),
        animationSpec = spring(),
        label = "reorder-item"
    )

    val isDraggedItem =
        state.isDragging &&
                state.sourceKey == key

    graphicsLayer {

        this.translationY =
            translationY

        alpha =
            if (isDraggedItem) {
                0f
            } else {
                1f
            }
    }
}