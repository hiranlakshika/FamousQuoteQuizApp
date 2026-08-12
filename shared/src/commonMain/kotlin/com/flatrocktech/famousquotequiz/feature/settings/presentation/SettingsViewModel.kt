package com.flatrocktech.famousquotequiz.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase.RestartQuizUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetSettingsUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.UpdateSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val restartQuizUseCase: RestartQuizUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getSettingsUseCase()
            .onEach { settings ->
                _state.update { it.copy(quizMode = settings.quizMode) }
            }
            .launchIn(viewModelScope)
    }

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.OnQuizModeChanged -> {
                viewModelScope.launch {
                    updateSettingsUseCase(Settings(quizMode = intent.quizMode))
                    restartQuizUseCase()
                }
            }
        }
    }
}
