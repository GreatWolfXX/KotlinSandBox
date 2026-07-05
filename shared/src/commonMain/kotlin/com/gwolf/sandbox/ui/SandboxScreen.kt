package com.gwolf.sandbox.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.gwolf.sandbox.core.*
import kotlinx.coroutines.isActive

@Composable
fun SandboxScreen() {

    val grid = remember { Grid() }
    val physicsEngine = remember { PhysicsEngine(grid) }
    val engine = remember { Engine(physicsEngine) }

    var ticks by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        var previousFrameNanos: Long? = null

        while (isActive) {
            withFrameNanos { frameTimeNanos ->
                if (previousFrameNanos != null) {
                    val deltaTime = (frameTimeNanos - previousFrameNanos!!) / 1_000_000_000f

                    engine.update(deltaTime)
                    ticks++
                }

                previousFrameNanos = frameTimeNanos
            }
        }
    }

    Grid(grid)
}

@Composable
fun Grid(grid: GridState) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        grid.updateTrigger

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