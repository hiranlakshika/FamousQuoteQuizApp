package com.flatrocktech.famousquotequiz.feature.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.flatrocktech.famousquotequiz.core.theme.Dimensions

@Composable
fun BadgeChip(
    text: String,
    containerColor: Color,
    contentColor: Color
) {
    Box(
        modifier = Modifier
            .background(containerColor, shape = RoundedCornerShape(Dimensions.RadiusFull))
            .padding(horizontal = Dimensions.spacingMediumLow, vertical = Dimensions.SpacingExtraSmall)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor
        )
    }
}