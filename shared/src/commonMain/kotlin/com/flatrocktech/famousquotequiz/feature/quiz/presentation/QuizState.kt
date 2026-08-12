package com.flatrocktech.famousquotequiz.feature.quiz.presentation

data class QuizState(
    val isLoading: Boolean = false,
    val currentQuestion: Int = 1,
    val totalQuestions: Int = 10,
    val quoteText: String = "",
    val quoteCategory: String = "Famous Quote",
    val choices: List<String> = emptyList(),
    val selectedChoiceIndex: Int? = null,
    val correctChoiceIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCorrect: Boolean? = null,
    val correctAnswerExplanation: String = "",
    val isQuizFinished: Boolean = false,
    val correctAnswersCount: Int = 0
)
