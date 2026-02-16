package com.example.recipebookapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Цвета из colors.xml
val DarkBlueBackground = Color(0xFF0B1A2F)      // background
val BlueSurface = Color(0xFF1E3A6A)             // surface
val BrightBlue = Color(0xFF7AD0FF)               // primary, accent, accent_green
val DarkBlue = Color(0xFF4AA3E0)                  // primary_dark
val MediumBlue = Color(0xFF2A4F8A)                // primary_light
val WhiteText = Color(0xFFFFFFFF)                  // text_primary
val LightBlueText = Color(0xFFB0E0FF)              // text_secondary

val LightColorPalette = lightColors(
    primary = BrightBlue,
    primaryVariant = DarkBlue,
    secondary = BrightBlue,
    background = DarkBlueBackground,
    surface = BlueSurface,
    onPrimary = DarkBlueBackground,
    onSecondary = DarkBlueBackground,
    onBackground = WhiteText,
    onSurface = WhiteText,
    onError = WhiteText
)

// Темная тема
val DarkColorPalette = darkColors(
    primary = BrightBlue,
    primaryVariant = DarkBlue,
    secondary = BrightBlue,
    background = DarkBlueBackground,
    surface = BlueSurface,
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText
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