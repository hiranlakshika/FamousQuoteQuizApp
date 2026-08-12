package com.flatrocktech.famousquotequiz.feature.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.profile_menu_account_details
import famousquotequiz.shared.generated.resources.profile_menu_help
import famousquotequiz.shared.generated.resources.profile_menu_notifications
import famousquotequiz.shared.generated.resources.profile_menu_quiz_history
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileMenuCard(
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier
) {
    val menuItems = listOf(
        Triple(
            Icons.Default.ManageAccounts,
            stringResource(Res.string.profile_menu_account_details),
            true
        ),
        Triple(
            Icons.Default.Notifications,
            stringResource(Res.string.profile_menu_notifications),
            true
        ),
        Triple(Icons.Default.History, stringResource(Res.string.profile_menu_quiz_history), true),
        Triple(
            Icons.AutoMirrored.Filled.HelpCenter,
            stringResource(Res.string.profile_menu_help),
            false
        ),
    )

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
        Column(modifier = Modifier.fillMaxWidth()) {
            menuItems.forEachIndexed { _, (icon, label, hasDivider) ->
                ProfileMenuItem(
                    icon = icon,
                    label = label,
                    colorScheme = colorScheme,
                    onClick = {}
                )
                if (hasDivider) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = Dimensions.PaddingMedium),
                        color = colorScheme.outlineVariant.copy(alpha = 0.4f),
                        thickness = Dimensions.DividerThickness
                    )
                }
            }
        }
    }
}