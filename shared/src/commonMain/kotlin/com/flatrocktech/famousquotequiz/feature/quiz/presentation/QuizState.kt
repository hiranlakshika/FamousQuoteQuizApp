package com.flatrocktech.famousquotequiz.feature.quiz.presentation

data class QuizState(
    val isLoading: Boolean = false,
    val currentQuestion: Int = 4,
    val totalQuestions: Int = 10,
    val quoteText: String = "\"The only thing we have to fear is fear itself.\"",
    val quoteCategory: String = "History / Politics",
    val choices: List<String> = listOf(
        "Winston Churchill",
        "Franklin D. Roosevelt",
        "Abraham Lincoln"
    ),
    val selectedChoiceIndex: Int? = null,
    val correctChoiceIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isCorrect: Boolean? = null,
    val correctAnswerExplanation: String = "Franklin D. Roosevelt said this during his first inaugural address in 1933."
)
