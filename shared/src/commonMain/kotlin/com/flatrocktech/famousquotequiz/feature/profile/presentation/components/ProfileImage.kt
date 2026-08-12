package com.flatrocktech.famousquotequiz.feature.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.profile_avatar_cd
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileImage(colorScheme: ColorScheme, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(Dimensions.AvatarSize)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorScheme.primary.copy(alpha = 0.15f),
                        colorScheme.secondary.copy(alpha = 0.2f)
                    )
                )
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(Dimensions.AvatarInnerSize)
                .clip(CircleShape)
                .background(colorScheme.surfaceVariant)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(Res.string.profile_avatar_cd),
                tint = colorScheme.onSurfaceVariant,
                modifier = Modifier.size(Dimensions.IconExtraLarge)
            )
        }
    }
}