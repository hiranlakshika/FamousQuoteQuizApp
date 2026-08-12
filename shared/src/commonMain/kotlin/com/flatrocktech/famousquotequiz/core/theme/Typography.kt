package com.flatrocktech.famousquotequiz.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayMedium = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.5).sp
    ),
    titleLarge = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.64).sp
    ),
    titleMedium = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.44).sp
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.14.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.14.sp
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.8.sp
    )
)
