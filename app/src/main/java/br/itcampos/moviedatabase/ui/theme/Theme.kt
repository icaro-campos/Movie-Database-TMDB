package br.itcampos.moviedatabase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Black,
    secondary = Black
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Black,
    secondary = Black
)

@Composable
fun MovieDatabaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}