package com.flatrocktech.famousquotequiz.feature.settings.presentation

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode

data class SettingsState(
    val isLoading: Boolean = false,
    val quizMode: QuizMode = QuizMode.BINARY
)
