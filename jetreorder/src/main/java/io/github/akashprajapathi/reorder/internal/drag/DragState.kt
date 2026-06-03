package io.github.akashprajapathi.jetreorder.internal.drag

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class DragState<K> {

    var isDragging by mutableStateOf(false)
        internal set

    var sourceKey by mutableStateOf<K?>(null)
        internal set

    var sourceIndex by mutableIntStateOf(-1)
        internal set

    var pointerY by mutableFloatStateOf(0f)
        internal set

    var grabOffsetY by mutableFloatStateOf(0f)
        internal set
}