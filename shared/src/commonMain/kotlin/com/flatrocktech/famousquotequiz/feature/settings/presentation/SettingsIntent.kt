package com.flatrocktech.famousquotequiz.feature.settings.presentation

sealed interface SettingsIntent {
    data class OnQuizModeToggled(val isMultipleChoice: Boolean) : SettingsIntent
}
