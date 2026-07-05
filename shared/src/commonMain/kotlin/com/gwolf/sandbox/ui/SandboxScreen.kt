package com.gwolf.sandbox.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.gwolf.sandbox.core.Element
import com.gwolf.sandbox.core.Engine
import com.gwolf.sandbox.core.Grid
import com.gwolf.sandbox.core.GridState
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

    Grid(grid)
}

@Composable
fun Grid(grid: GridState) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleX = size.width / grid.width
        val scaleY = size.height / grid.height

        val scale = minOf(scaleX, scaleY)

        val offsetX = (size.width - (grid.width * scale)) / 2
        val offsetY = (size.height - (grid.height * scale)) / 2

        for (y in 0 until grid.height) {
            for (x in 0 until grid.width) {
                val element = grid.getElement(x, y)

                if (element != Element.EMPTY) {
                    drawRect(
                        color = ElementColor.getColor(element),
                        topLeft = Offset(offsetX + (x * scale), offsetY + (y * scale)),
                        size = Size(scale, scale)
                    )
                }
            }
        }
    }
}