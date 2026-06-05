package io.github.akashprajapathi.jetreorder.internal.effect

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import io.github.akashprajapathi.jetreorder.api.ReorderableState
import io.github.akashprajapathi.jetreorder.internal.reorder.model.ItemLayout

@Composable
internal fun <T, K> ReorderableEffects(
    state: ReorderableState<K>,
    listState: LazyListState,
    data: List<T>,
    key: (T) -> K
) {

    LaunchedEffect(
        listState,
        data
    ) {

        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo
        }.collect { visibleItems ->

            state.updateItemLayouts(

                visibleItems.map {

                    ItemLayout(
                        key = key(data[it.index]),
                        index = it.index,
                        start = it.offset.toFloat(),
                        end = (it.offset + it.size).toFloat()
                    )
                }
            )
        }
    }

    LaunchedEffect(
        state.targetIndex,
        state.isDragging
    ) {

        state.updateAnimationOffsets()
    }
}