package io.github.akashprajapathi.jetreorder.internal.autoscroll

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue

internal class AutoScrollState {

    var containerHeight by mutableFloatStateOf(0f)
        internal set

    var totalScrolledDistance by mutableFloatStateOf(0f)
        internal set
}