package com.flatrocktech.famousquotequiz.feature.profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.flatrocktech.famousquotequiz.core.theme.Dimensions

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
    value: String,
    label: String
) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = modifier
            .shadow(
                elevation = Dimensions.ElevationMedium,
                shape = RoundedCornerShape(Dimensions.RadiusMedium),
                ambientColor = colorScheme.primary.copy(alpha = 0.05f),
                spotColor = colorScheme.primary.copy(alpha = 0.05f)
            ),
        shape = RoundedCornerShape(Dimensions.RadiusMedium),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.ElevationNone)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.PaddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingExtraSmall)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(Dimensions.IconLarge)
                    .padding(bottom = Dimensions.SpacingTiny)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge,
                color = colorScheme.onSurface
            )
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = colorScheme.onSurfaceVariant
            )
        }
    }
}