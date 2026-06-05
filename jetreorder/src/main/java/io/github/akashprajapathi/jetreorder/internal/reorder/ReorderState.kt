package io.github.akashprajapathi.jetreorder.internal.reorder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.akashprajapathi.jetreorder.internal.reorder.model.ItemLayout

internal class ReorderState<K> {

    var targetKey by mutableStateOf<K?>(null)
        internal set

    var targetIndex by mutableIntStateOf(-1)
        internal set

    var itemLayouts by mutableStateOf<List<ItemLayout<K>>>(
        emptyList()
    )
        internal set
}