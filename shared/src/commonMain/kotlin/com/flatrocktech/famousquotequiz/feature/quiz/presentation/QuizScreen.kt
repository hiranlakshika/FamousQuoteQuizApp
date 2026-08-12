package com.flatrocktech.famousquotequiz.feature.quiz.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.flatrocktech.famousquotequiz.core.presentation.components.PrimaryButton
import com.flatrocktech.famousquotequiz.core.theme.Dimensions
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.components.ChoicesSection
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.components.QuizHeader
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.components.QuoteCard
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.components.ResultDialog
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.quiz_restart
import famousquotequiz.shared.generated.resources.quiz_results_score
import famousquotequiz.shared.generated.resources.quiz_results_title
import famousquotequiz.shared.generated.resources.quiz_submit
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun QuizScreen(
    viewModel: QuizViewModel = koinViewModel()
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
        if (state.isQuizFinished) {
            QuizResultsSection(
                correctAnswersCount = state.correctAnswersCount,
                totalQuestions = state.totalQuestions,
                onRestart = { viewModel.onIntent(QuizIntent.OnRestartQuiz) },
                colorScheme = colorScheme
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = Dimensions.ScreenPadding,
                        vertical = Dimensions.PaddingLarge
                    )
                    .widthIn(max = Dimensions.ProfileMaxWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.SpacingLarge)
            ) {
                QuizHeader(
                    currentQuestion = state.currentQuestion,
                    totalQuestions = state.totalQuestions
                )

                QuoteCard(
                    quoteText = state.quoteText,
                    category = state.quoteCategory
                )

                ChoicesSection(
                    choices = state.choices,
                    selectedIndex = state.selectedChoiceIndex,
                    correctIndex = state.correctChoiceIndex,
                    isSubmitted = state.isAnswerSubmitted,
                    onChoiceSelected = { viewModel.onIntent(QuizIntent.OnChoiceSelected(it)) }
                )

                PrimaryButton(
                    text = stringResource(Res.string.quiz_submit),
                    onClick = { viewModel.onIntent(QuizIntent.OnSubmitAnswer) },
                    enabled = state.selectedChoiceIndex != null && !state.isAnswerSubmitted,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(Dimensions.SpacingSmall))
            }
        }

        if (state.isAnswerSubmitted && state.isCorrect != null && !state.isQuizFinished) {
            ResultDialog(
                isCorrect = state.isCorrect!!,
                correctAnswer = state.choices.getOrElse(state.correctChoiceIndex ?: 0) { "" },
                onNextQuestion = { viewModel.onIntent(QuizIntent.OnNextQuestion) }
            )
        }
    }
}

@Composable
private fun QuizResultsSection(
    correctAnswersCount: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    colorScheme: androidx.compose.material3.ColorScheme
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.ScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.quiz_results_title),
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.primary
        )

        Spacer(Modifier.height(Dimensions.SpacingMedium))

        Text(
            text = stringResource(
                Res.string.quiz_results_score,
                correctAnswersCount,
                totalQuestions
            ),
            style = MaterialTheme.typography.titleLarge,
            color = colorScheme.onSurface
        )

        Spacer(Modifier.height(Dimensions.SpacingLarge))

        PrimaryButton(
            text = stringResource(Res.string.quiz_restart),
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
