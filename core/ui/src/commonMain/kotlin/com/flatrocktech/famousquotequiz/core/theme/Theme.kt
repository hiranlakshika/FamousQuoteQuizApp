package com.flatrocktech.famousquotequiz.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    secondary = SecondaryColor,
    onSecondary = OnPrimary,
    error = ErrorColor,
    onError = OnPrimary,
    background = SurfaceColor,
    surface = CardBackground,
    onSurface = PrimaryColor,
    onSurfaceVariant = OnSurfaceVar,
    outline = OutlineColor,
    surfaceVariant = SurfaceDim
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    secondary = SecondaryColor,
    onSecondary = OnPrimary,
    error = ErrorColor,
    onError = OnPrimary,
    background = SurfaceColor,
    surface = CardBackground,
    onSurface = PrimaryColor,
    onSurfaceVariant = OnSurfaceVar,
    outline = OutlineColor,
    surfaceVariant = SurfaceDim
)

@Composable
fun FamousQuoteQuizTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
