package com.flatrocktech.famousquotequiz.feature.settings.presentation

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode

sealed interface SettingsIntent {
    data class OnQuizModeChanged(val quizMode: QuizMode) : SettingsIntent
}
