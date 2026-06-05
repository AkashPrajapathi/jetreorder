package io.github.akashprajapathi.jetreorder.api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import io.github.akashprajapathi.jetreorder.internal.effect.ReorderableEffects
import io.github.akashprajapathi.jetreorder.internal.overlay.DragOverlay

@Composable
fun <T, K> ReorderLazyColumn(
    data: List<T>,
    key: (T) -> K,
    onMove: (from: Int, to: Int) -> Unit,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {

    val listState =
        rememberLazyListState()

    val state =
        rememberReorderableState<K>(
            listState = listState,
            onMove = onMove
        )

    ReorderableEffects(
        state = state,
        listState = listState,
        data = data,
        key = key
    )

    Box(
        modifier = modifier
            .onSizeChanged {

                state.containerHeight =
                    it.height.toFloat()
            }
            .reorderable(state)
    ) {

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {

            items(
                items = data,
                key = { item ->
                    key(item) as Any
                }
            ) { item ->

                Box(
                    modifier = Modifier.reorderItem(
                        key = key(item),
                        state = state
                    )
                ) {

                    itemContent(item)
                }
            }
        }

        DragOverlay(
            isDragging = state.isDragging,
            offsetY = state.visualOverlayOffsetY,
            onHeightChanged = {
                state.overlayHeight = it
            }
        ) {

            val draggedItem =
                data.firstOrNull {

                    key(it) ==
                            state.sourceKey
                }

            if (draggedItem != null) {

                itemContent(
                    draggedItem
                )
            }
        }
    }
}