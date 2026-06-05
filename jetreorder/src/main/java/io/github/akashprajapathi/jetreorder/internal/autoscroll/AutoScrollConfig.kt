package io.github.akashprajapathi.jetreorder.internal.autoscroll

internal data class AutoScrollConfig(
    val edgeSize: Float = 150f,
    val maxScrollSpeed: Float = 40f,
    val frameDelay: Long = 16L
)