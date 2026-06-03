package io.github.akashprajapathi.jetreorder.internal.reorder.model

internal data class ItemLayout<K>(
    val key: K,
    val index: Int,
    val start: Float,
    val end: Float
)