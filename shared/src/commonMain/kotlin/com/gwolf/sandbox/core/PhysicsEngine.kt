package com.gwolf.sandbox.core

class PhysicsEngine(private val gridState: GridState) : PhysicsEngineState {
    private var isStepRight = true

    override fun updatePhysics() {
        for (y in gridState.height.dec() downTo 0) {
            if (isStepRight) {
                for (x in 0 until gridState.width) {
                    updateElement(x, y)
                }
            } else {
                for (x in gridState.width.dec() downTo 0) {
                    updateElement(x, y)
                }
            }
        }

        isStepRight = !isStepRight
        gridState.notifyPhysicsTick()
    }

    private fun updateElement(x: Int, y: Int) {
        val currentElement = gridState.getElement(x, y)
        val nextY = y.inc()
        val leftX = x.dec()
        val rightX = x.inc()

        if (currentElement != Element.SAND) return

        if (gridState.inBounds(x, nextY) && gridState.getElement(x, nextY) == Element.EMPTY) {
            gridState.setElement(x, y, Element.EMPTY)
            gridState.setElement(x, nextY, currentElement)
            return
        }

        if (gridState.inBounds(leftX, nextY) && gridState.getElement(leftX, nextY) == Element.EMPTY) {
            gridState.setElement(x, y, Element.EMPTY)
            gridState.setElement(leftX, nextY, currentElement)
            return
        }

        if (gridState.inBounds(rightX, nextY) && gridState.getElement(rightX, nextY) == Element.EMPTY) {
            gridState.setElement(x, y, Element.EMPTY)
            gridState.setElement(rightX, nextY, currentElement)
            return
        }
    }
}