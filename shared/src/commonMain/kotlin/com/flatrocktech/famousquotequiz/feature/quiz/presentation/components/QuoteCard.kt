package com.flatrocktech.famousquotequiz.feature.quiz.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.flatrocktech.famousquotequiz.core.theme.Dimensions

@Composable
fun QuoteCard(
    quoteText: String,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

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
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "\u201C",
                fontSize = Dimensions.QuoteMarkWatermarkSize,
                color = colorScheme.secondary.copy(alpha = 0.12f),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = Dimensions.SpacingSmall, y = -Dimensions.SpacingSmall)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Dimensions.PaddingLarge,
                        end = Dimensions.PaddingLarge,
                        top = Dimensions.PaddingLarge,
                        bottom = Dimensions.PaddingMedium
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingLarge)
            ) {
                Text(
                    text = quoteText,
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    color = colorScheme.primary,
                    textAlign = TextAlign.Center,
                    lineHeight = Dimensions.QuoteLineHeight
                )
            }
        }
    }
}
