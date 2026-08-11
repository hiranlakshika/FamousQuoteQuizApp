package com.flatrocktech.famousquotequiz.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.error_invalid_email
import famousquotequiz.shared.generated.resources.error_password_required
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnEmailChanged -> {
                _state.update { it.copy(email = intent.email, emailError = null) }
            }

            is LoginIntent.OnPasswordChanged -> {
                _state.update { it.copy(password = intent.password, passwordError = null) }
            }

            LoginIntent.OnLoginClicked -> {
                validateAndLogin()
            }
        }
    }

    private fun validateAndLogin() {
        val currentState = _state.value
        var emailError = if (currentState.email.isBlank() || !currentState.email.contains("@")) {
            Res.string.error_invalid_email
        } else null

        var passwordError = if (currentState.password.isBlank()) {
            Res.string.error_password_required
        } else null

        _state.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError
            )
        }

        if (emailError == null && passwordError == null) {
            viewModelScope.launch {
                _state.update { it.copy(isLoading = true) }
                // Simulate network call or perform actual login
                // For now, we just set success
                _state.update { it.copy(isLoading = false, isLoginSuccess = true) }
            }
        }
    }
}
