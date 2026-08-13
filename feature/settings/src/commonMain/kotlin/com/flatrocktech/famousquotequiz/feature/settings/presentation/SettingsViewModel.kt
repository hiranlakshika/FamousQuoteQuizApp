package com.flatrocktech.famousquotequiz.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase.RestartQuizUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetQuizModeUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.UpdateQuizModeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getQuizModeUseCase: GetQuizModeUseCase,
    private val updateQuizModeUseCase: UpdateQuizModeUseCase,
    private val restartQuizUseCase: RestartQuizUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val settings = getQuizModeUseCase()
            _state.update { it.copy(quizMode = settings.quizMode) }
        }
    }

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.OnQuizModeChanged -> {
                viewModelScope.launch {
                    val newSettings = Settings(quizMode = intent.quizMode)
                    updateQuizModeUseCase(newSettings)
                    _state.update { it.copy(quizMode = intent.quizMode) }
                    restartQuizUseCase()
                }
            }
        }
    }
}
