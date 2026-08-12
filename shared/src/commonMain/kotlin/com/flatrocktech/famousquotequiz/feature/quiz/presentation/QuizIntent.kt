package com.flatrocktech.famousquotequiz.feature.quiz.presentation

sealed interface QuizIntent {
    data class OnChoiceSelected(val index: Int) : QuizIntent
    data object OnSubmitAnswer : QuizIntent
    data object OnNextQuestion : QuizIntent
    data object OnDismissResult : QuizIntent
    data object OnRestartQuiz : QuizIntent
}
