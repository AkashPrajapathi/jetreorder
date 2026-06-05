package io.github.akashprajapathi.jetreorder.internal.animation

import androidx.compose.runtime.mutableStateMapOf

internal class ReorderAnimationState<K> {

    internal val offsets =
        mutableStateMapOf<K, Float>()
}