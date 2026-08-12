package com.flatrocktech.famousquotequiz.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.feature.profile.presentation.components.BadgeChip
import com.flatrocktech.famousquotequiz.feature.profile.presentation.components.LogoutButton
import com.flatrocktech.famousquotequiz.feature.profile.presentation.components.ProfileImage
import com.flatrocktech.famousquotequiz.feature.profile.presentation.components.ProfileMenuCard
import com.flatrocktech.famousquotequiz.feature.profile.presentation.components.StatCard
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.profile_badge_pro
import famousquotequiz.shared.generated.resources.profile_badge_top
import famousquotequiz.shared.generated.resources.profile_stat_quizzes
import famousquotequiz.shared.generated.resources.profile_stat_score
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onLogout: () -> Unit
) {
    val state by viewModel.state.collectAsState()
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.ScreenPadding)
        ) {
            ProfileHeaderSection(state = state, colorScheme = colorScheme)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimensions.PaddingMedium)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Quiz,
                    iconTint = colorScheme.secondary,
                    value = state.quizzesTaken.toString(),
                    label = stringResource(Res.string.profile_stat_quizzes)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.WorkspacePremium,
                    iconTint = colorScheme.tertiary.takeIf { it != Color.Black }
                        ?: colorScheme.secondary.copy(alpha = 0.75f),
                    value = "${state.avgScore}%",
                    label = stringResource(Res.string.profile_stat_score)
                )
            }

            ProfileMenuCard(colorScheme = colorScheme)

            LogoutButton(onLogout = onLogout)

            Spacer(Modifier.height(Dimensions.SpacingSmall))
        }
    }
}

@Composable
private fun ProfileHeaderSection(
    state: ProfileState,
    colorScheme: ColorScheme
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingSmall),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimensions.SpacingSmall)
    ) {

        ProfileImage(colorScheme)

        Spacer(Modifier.height(Dimensions.SpacingExtraSmall))

        Text(
            text = state.name,
            style = MaterialTheme.typography.titleMedium,
            color = colorScheme.onSurface
        )

        Text(
            text = state.email,
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onSurfaceVariant
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimensions.SpacingSmall),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = Dimensions.SpacingExtraSmall)
        ) {
            BadgeChip(
                text = stringResource(Res.string.profile_badge_pro),
                containerColor = colorScheme.primary.copy(alpha = 0.10f),
                contentColor = colorScheme.primary
            )
            BadgeChip(
                text = stringResource(Res.string.profile_badge_top),
                containerColor = colorScheme.secondary.copy(alpha = 0.10f),
                contentColor = colorScheme.secondary
            )
        }
    }
}
