package com.flatrocktech.famousquotequiz.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.feature.profile.generated.resources.Res
import famousquotequiz.feature.profile.generated.resources.profile_logout
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogoutButton(onLogout: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.SecondaryButtonHeight)
            .shadow(
                elevation = Dimensions.ElevationSmall,
                shape = RoundedCornerShape(Dimensions.RadiusFull),
                ambientColor = colorScheme.error.copy(alpha = 0.08f),
                spotColor = colorScheme.error.copy(alpha = 0.08f)
            )
            .clip(RoundedCornerShape(Dimensions.RadiusFull))
            .clickable(onClick = onLogout),
        color = colorScheme.errorContainer,
        shape = RoundedCornerShape(Dimensions.RadiusFull)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.PaddingLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = colorScheme.onErrorContainer,
                modifier = Modifier.size(Dimensions.IconSmall)
            )
            Spacer(Modifier.size(Dimensions.SpacingSmall))
            Text(
                text = stringResource(Res.string.profile_logout),
                style = MaterialTheme.typography.labelLarge,
                color = colorScheme.onErrorContainer
            )
        }
    }
}