package com.flatrocktech.famousquotequiz.feature.quiz.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.flatrocktech.famousquotequiz.core.presentation.components.PrimaryButton
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.quiz_correct
import famousquotequiz.shared.generated.resources.quiz_incorrect
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResultDialog(
    isCorrect: Boolean,
    correctAnswer: String,
    onNextQuestion: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.primary.copy(alpha = 0.20f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = Dimensions.DialogMaxWidth)
                .fillMaxWidth()
                .padding(horizontal = Dimensions.ScreenPadding),
            shape = RoundedCornerShape(Dimensions.RadiusMedium),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.ElevationLarge)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.PaddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingMedium)
            ) {
                Box(
                    modifier = Modifier
                        .size(Dimensions.BrandingLogoSize)
                        .clip(CircleShape)
                        .background(
                            if (isCorrect) Color(0xFFE8F5E9) else colorScheme.errorContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                        contentDescription = null,
                        tint = if (isCorrect) Color(0xFF2E7D32) else colorScheme.error,
                        modifier = Modifier.size(Dimensions.IconHuge)
                    )
                }

                // Result message
                Text(
                    text = if (isCorrect) stringResource(Res.string.quiz_correct, correctAnswer)
                    else stringResource(Res.string.quiz_incorrect, correctAnswer),
                    style = MaterialTheme.typography.titleSmall,
                    color = colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                // OK button as per requirement "Once the OK button is clicked"
                PrimaryButton(
                    text = "OK",
                    onClick = onNextQuestion
                )
            }
        }
    }
}
