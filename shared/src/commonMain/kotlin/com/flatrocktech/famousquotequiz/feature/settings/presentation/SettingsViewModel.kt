package com.flatrocktech.famousquotequiz.feature.settings.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.OnQuizModeToggled -> {
                _state.update { it.copy(isMultipleChoiceMode = intent.isMultipleChoice) }
            }
        }
    }
}
