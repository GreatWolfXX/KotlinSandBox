package com.gwolf.sandbox.ui

import androidx.compose.ui.graphics.Color
import com.gwolf.sandbox.core.Element
import java.util.Objects

object ElementColor {
    val PALETTE = mapOf(
        Element.EMPTY to Color.Transparent,
        Element.SAND to Color(0xFFE6C280),
        Element.WALL to Color(0xFF808080)
    )

    fun getColor(element: Int): Color {
        return  PALETTE[element] ?: Color.Magenta
    }
}