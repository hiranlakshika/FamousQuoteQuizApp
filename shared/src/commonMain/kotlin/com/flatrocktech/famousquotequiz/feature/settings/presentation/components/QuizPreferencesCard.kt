package com.flatrocktech.famousquotequiz.feature.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.settings_quiz_mode_binary
import famousquotequiz.shared.generated.resources.settings_quiz_mode_info
import famousquotequiz.shared.generated.resources.settings_quiz_mode_label
import famousquotequiz.shared.generated.resources.settings_quiz_mode_multiple
import famousquotequiz.shared.generated.resources.settings_section_quiz
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuizPreferencesCard(
    quizMode: QuizMode,
    onModeChanged: (QuizMode) -> Unit,
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
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
                .padding(Dimensions.PaddingLarge)
        ) {
            Text(
                text = stringResource(Res.string.settings_section_quiz),
                style = MaterialTheme.typography.titleSmall,
                color = colorScheme.primary
            )

            Spacer(Modifier.height(Dimensions.SpacingMedium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(Res.string.settings_quiz_mode_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = colorScheme.onSurface
                    )
                    Spacer(Modifier.height(Dimensions.SpacingTiny))
                    Text(
                        text = if (quizMode == QuizMode.MULTIPLE_CHOICE)
                            stringResource(Res.string.settings_quiz_mode_multiple)
                        else
                            stringResource(Res.string.settings_quiz_mode_binary),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSurfaceVariant
                    )
                }
                Switch(
                    checked = quizMode == QuizMode.MULTIPLE_CHOICE,
                    onCheckedChange = { isMultiple ->
                        onModeChanged(if (isMultiple) QuizMode.MULTIPLE_CHOICE else QuizMode.BINARY)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorScheme.surface,
                        checkedTrackColor = colorScheme.secondary,
                        uncheckedThumbColor = colorScheme.surface,
                        uncheckedTrackColor = colorScheme.outlineVariant,
                        uncheckedBorderColor = colorScheme.outlineVariant
                    )
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = Dimensions.SpacingMedium),
                color = colorScheme.surfaceVariant,
                thickness = Dimensions.DividerThickness
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.SpacingSmall),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = colorScheme.secondary,
                    modifier = Modifier
                        .size(Dimensions.IconMedium)
                        .padding(top = Dimensions.SpacingTiny)
                )
                Text(
                    text = stringResource(Res.string.settings_quiz_mode_info),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
