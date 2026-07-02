package com.gwolf.sandbox.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import com.gwolf.sandbox.core.Engine
import com.gwolf.sandbox.core.Grid
import kotlinx.coroutines.isActive

@Composable
fun SandboxScreen() {

    val grid = remember { Grid() }
    val engine = remember { Engine(grid) }

    LaunchedEffect(Unit) {
        var previousTimeNanos = System.nanoTime()

        while (isActive) {
            withFrameNanos { frameTimeNanos ->
                val deltaTime = (frameTimeNanos - previousTimeNanos) / 1_000_000_000f
                previousTimeNanos = frameTimeNanos

                engine.update(deltaTime)
            }
        }
    }
}