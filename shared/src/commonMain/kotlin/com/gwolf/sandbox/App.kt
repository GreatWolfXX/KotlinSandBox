package com.gwolf.sandbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gwolf.sandbox.theme.AppTheme
import com.gwolf.sandbox.ui.SandboxScreen

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    SandboxScreen()
}
