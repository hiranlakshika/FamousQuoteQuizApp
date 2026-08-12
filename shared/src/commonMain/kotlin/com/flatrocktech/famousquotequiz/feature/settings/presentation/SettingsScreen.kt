package com.flatrocktech.famousquotequiz.feature.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.feature.settings.presentation.components.QuizPreferencesCard
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.settings_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        colorScheme.background
                    ),
                    center = Offset(Float.POSITIVE_INFINITY, 0f),
                    radius = 1400f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Dimensions.ScreenPadding, vertical = Dimensions.PaddingLarge)
                .widthIn(max = Dimensions.ProfileMaxWidth),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingMedium)
        ) {
            Text(
                text = stringResource(Res.string.settings_title),
                style = MaterialTheme.typography.titleLarge,
                color = colorScheme.primary
            )

            Spacer(Modifier.height(Dimensions.SpacingExtraSmall))

            QuizPreferencesCard(
                isMultipleChoiceMode = state.isMultipleChoiceMode,
                onModeToggled = { viewModel.onIntent(SettingsIntent.OnQuizModeToggled(it)) },
                colorScheme = colorScheme
            )

            Spacer(Modifier.height(Dimensions.SpacingSmall))
        }
    }
}
