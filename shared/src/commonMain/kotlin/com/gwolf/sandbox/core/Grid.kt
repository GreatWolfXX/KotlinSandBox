package com.gwolf.sandbox.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class Grid : GridState {

    override val width: Int = GRID_WIDTH
    override val height: Int = GRID_HEIGHT

    val grid = IntArray(width * height)

    override var updateTrigger by mutableStateOf(0L)
        private set

    inline fun getIndex(x: Int, y: Int): Int {
        return y * width + x
    }

    inline fun getX(index: Int): Int {
        return index % width
    }

    inline fun getY(index: Int): Int {
        return index / width
    }

    override fun getElement(x: Int, y: Int): Int {
        return grid[getIndex(x, y)]
    }

    override fun setElement(x: Int, y: Int, value: Int) {
        grid[getIndex(x, y)] = value
    }

    override fun inBounds(x: Int, y: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }

    override fun notifyPhysicsTick() {
        updateTrigger++
    }

    init {
        for (x in 0 until width) {
            setElement(x, height - 10, Element.WALL)
        }

        for (i in 0 until (width * height / 10)) { // заполняем ~10% пространства
            val randomX = Random.nextInt(0, width)
            val randomY = Random.nextInt(0, height - 20) // чуть выше платформы

            setElement(randomX, randomY, Element.SAND)
        }

        val centerX = width / 2
        val centerY = height / 2
        for (i in -5..5) {
            if (inBounds(centerX + i, centerY)) setElement(centerX + i, centerY, Element.WALL)
            if (inBounds(centerX, centerY + i)) setElement(centerX, centerY + i, Element.SAND)
        }
    }
}