package com.flatrocktech.famousquotequiz.feature.quiz.presentation

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode

data class QuizState(
    val isLoading: Boolean = false,
    val sessionId: String? = null,
    val currentQuestion: Int = 1,
    val totalQuestions: Int = 10,
    val quoteText: String = "",
    val proposedAuthor: String? = null,
    val choices: List<String> = emptyList(),
    val selectedChoiceIndex: Int? = null,
    val correctChoiceIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCorrect: Boolean? = null,
    val correctAnswerExplanation: String = "",
    val isQuizFinished: Boolean = false,
    val correctAnswersCount: Int = 0,
    val quizMode: QuizMode = QuizMode.BINARY
)
