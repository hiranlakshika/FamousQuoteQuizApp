package com.flatrocktech.famousquotequiz.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.feature.auth.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    fun onIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.OnLogoutClicked -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = logoutUseCase()
            _state.update {
                when (result) {
                    is Result.Success -> it.copy(isLoading = false, isLogoutSuccess = true)
                    is Result.Error -> it.copy(isLoading = false)
                }
            }
        }
    }
}
