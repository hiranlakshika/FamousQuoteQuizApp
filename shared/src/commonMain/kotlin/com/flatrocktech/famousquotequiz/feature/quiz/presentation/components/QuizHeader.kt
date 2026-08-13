package com.flatrocktech.famousquotequiz.feature.quiz.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.flatrocktech.famousquotequiz.core.theme.ChallengeLabelStyle
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.quiz_daily_challenge
import famousquotequiz.shared.generated.resources.quiz_question_of
import famousquotequiz.shared.generated.resources.quiz_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuizHeader(
    currentQuestion: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val progress = currentQuestion.toFloat() / totalQuestions.toFloat()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingTiny)) {
            Text(
                text = stringResource(Res.string.quiz_daily_challenge).uppercase(),
                style = ChallengeLabelStyle,
                color = colorScheme.secondary
            )
            Text(
                text = stringResource(Res.string.quiz_title),
                style = MaterialTheme.typography.titleSmall,
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingSmall)
        ) {
            Text(
                text = stringResource(Res.string.quiz_question_of, currentQuestion, totalQuestions),
                style = MaterialTheme.typography.labelLarge,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End
            )
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .width(Dimensions.ProgressBarWidth)
                    .height(Dimensions.ProgressBarHeight)
                    .clip(RoundedCornerShape(Dimensions.RadiusFull)),
                color = colorScheme.secondary,
                trackColor = colorScheme.surfaceVariant
            )
        }
    }
}
