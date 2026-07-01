package core

class Grid : GridState {

    override val width: Int = GRID_WIDTH
    override val height: Int = GRID_HEIGHT

    val grid = IntArray(width * height)

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
}