package core

interface GridState {
    val width: Int
    val height: Int
    fun getElement(x: Int, y: Int): Int
    fun setElement(x: Int, y: Int, value: Int)
    fun inBounds(x: Int, y: Int): Boolean
}