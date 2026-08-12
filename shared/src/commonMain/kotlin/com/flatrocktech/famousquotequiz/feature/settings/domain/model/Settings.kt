package com.flatrocktech.famousquotequiz.feature.settings.domain.model

data class Settings(
    val quizMode: QuizMode = QuizMode.BINARY,
    val isDarkMode: Boolean = false
)

enum class QuizMode {
    BINARY,
    MULTIPLE_CHOICE
}