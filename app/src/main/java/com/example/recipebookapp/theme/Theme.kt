package com.example.recipebookapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Цвета для светлой темы
val LightBackground = Color(0xFFF5F9FF)      // Светло-голубой фон
val LightSurface = Color(0xFFFFFFFF)         // Белые карточки
val LightPrimary = Color(0xFF2563EB)         // Синий
val LightText = Color(0xFF1E293B)            // Темно-серый текст

// Цвета для темной темы
val DarkBackground = Color(0xFF0B1A2F)       // Темно-синий фон
val DarkSurface = Color(0xFF1E3A6A)          // Темно-синие карточки
val DarkPrimary = Color(0xFF7AD0FF)          // Ярко-голубой
val DarkText = Color(0xFFFFFFFF)              // Белый текст

val LightColorPalette = lightColors(
    primary = LightPrimary,
    primaryVariant = LightPrimary,
    secondary = LightPrimary,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = LightText,
    onSurface = LightText,
    onError = Color.White
)
val DarkColorPalette = darkColors(
    primary = DarkPrimary,
    primaryVariant = DarkPrimary,
    secondary = DarkPrimary,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onBackground = DarkText,
    onSurface = DarkText,
    onError = Color.White
)

@Composable
fun RecipeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}