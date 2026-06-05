package io.github.akashprajapathi.jetreorder.internal.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged

@Composable
internal fun DragOverlay(
    isDragging: Boolean,
    offsetY: Float,
    onHeightChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    if (!isDragging) {
        return
    }

    Box(
        modifier = modifier
            .onSizeChanged {
                onHeightChanged(
                    it.height.toFloat()
                )
            }
            .graphicsLayer {

                translationY = offsetY

                scaleX = 1.03f
                scaleY = 1.03f

                alpha = 0.95f

                shadowElevation = 24f
            }
    ) {
        content()
    }
}