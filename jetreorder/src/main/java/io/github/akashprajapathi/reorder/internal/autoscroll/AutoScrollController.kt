package io.github.akashprajapathi.jetreorder.internal.autoscroll

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import io.github.akashprajapathi.jetreorder.internal.drag.DragState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class AutoScrollController<K>(
    private val lazyListState: LazyListState,
    private val autoScrollState: AutoScrollState,
    private val dragState: DragState<K>,
    private val scope: CoroutineScope,
    private val config: AutoScrollConfig
) {

    private var autoScrollJob: Job? = null

    internal fun start() {

        if (autoScrollJob?.isActive == true) {
            return
        }

        autoScrollJob = scope.launch {

            while (isActive) {

                val scrollAmount =
                    calculateScrollAmount()

                if (scrollAmount != 0f) {

                    val consumed =
                        lazyListState.scrollBy(scrollAmount)

                    autoScrollState.totalScrolledDistance += consumed
                }

                delay(config.frameDelay)
            }
        }
    }

    internal fun stop() {

        autoScrollJob?.cancel()

        autoScrollJob = null

        autoScrollState.totalScrolledDistance = 0f
    }

    private fun calculateScrollAmount(): Float {

        val pointerY =
            dragState.pointerY

        val containerHeight =
            autoScrollState.containerHeight

        if (containerHeight <= 0f) {
            return 0f
        }

        return when {

            pointerY < config.edgeSize -> {

                val progress =
                    (config.edgeSize - pointerY) /
                            config.edgeSize

                -(progress * config.maxScrollSpeed)
            }

            pointerY > containerHeight - config.edgeSize -> {

                val progress =
                    (pointerY - (containerHeight - config.edgeSize)) /
                            config.edgeSize

                progress * config.maxScrollSpeed
            }

            else -> 0f
        }
    }
}