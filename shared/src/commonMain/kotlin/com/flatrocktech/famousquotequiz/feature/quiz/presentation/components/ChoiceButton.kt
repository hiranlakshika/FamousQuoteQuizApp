package com.flatrocktech.famousquotequiz.feature.quiz.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.flatrocktech.famousquotequiz.core.theme.Dimensions

enum class ChoiceState { Default, Selected, Correct, Incorrect }

@Composable
fun ChoiceButton(
    text: String,
    state: ChoiceState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    val (borderColor, textColor, borderWidth, iconTint, showIcon) = when (state) {
        ChoiceState.Default -> ChoiceStyle(
            border = colorScheme.outlineVariant,
            text = colorScheme.onSurface,
            borderWidth = Dimensions.BorderThin,
            icon = colorScheme.outline,
            showIcon = false
        )
        ChoiceState.Selected -> ChoiceStyle(
            border = colorScheme.secondary,
            text = colorScheme.secondary,
            borderWidth = Dimensions.BorderThick,
            icon = colorScheme.secondary,
            showIcon = true
        )
        ChoiceState.Correct -> ChoiceStyle(
            border = Color(0xFF2E7D32),
            text = Color(0xFF2E7D32),
            borderWidth = Dimensions.BorderThick,
            icon = Color(0xFF2E7D32),
            showIcon = true
        )
        ChoiceState.Incorrect -> ChoiceStyle(
            border = colorScheme.error,
            text = colorScheme.error,
            borderWidth = Dimensions.BorderThick,
            icon = colorScheme.error,
            showIcon = true
        )
    }

    OutlinedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimensions.RadiusSmall),
        border = BorderStroke(borderWidth, borderColor),
        colors = androidx.compose.material3.CardDefaults.outlinedCardColors(
            containerColor = colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Dimensions.PaddingLarge,
                    vertical = Dimensions.PaddingMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor,
                modifier = Modifier.weight(1f)
            )
            if (showIcon) {
                Icon(
                    imageVector = if (state == ChoiceState.Default)
                        Icons.Default.RadioButtonUnchecked
                    else
                        Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(Dimensions.IconMedium)
                )
            }
        }
    }
}

private data class ChoiceStyle(
    val border: Color,
    val text: Color,
    val borderWidth: androidx.compose.ui.unit.Dp,
    val icon: Color,
    val showIcon: Boolean
)
