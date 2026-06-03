package io.github.akashprajapathi.jetreorder.util

fun <T> MutableList<T>.move(
    from: Int,
    to: Int
) {

    if (from == to) return

    add(
        to,
        removeAt(from)
    )
}