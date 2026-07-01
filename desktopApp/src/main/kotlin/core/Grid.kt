package core

class Grid {
    val grid = IntArray(GRID_WIDTH * GRID_HEIGHT)

    inline fun getIndex(x: Int, y: Int): Int {
        return y * GRID_WIDTH + x
    }

    inline fun getX(index: Int): Int {
        return index % GRID_WIDTH
    }

    inline fun getY(index: Int): Int {
        return index / GRID_WIDTH
    }

    fun inBounds(x: Int, y: Int): Boolean {
        return x in 0 until GRID_WIDTH && y in 0 until GRID_HEIGHT
    }
}