package com.flatrocktech.famousquotequiz.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.feature.auth.domain.usecase.LoginUseCase
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.error_invalid_email
import famousquotequiz.shared.generated.resources.error_password_required
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
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
        val emailError = if (currentState.email.isBlank() || !currentState.email.contains("@")) {
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

                val result = loginUseCase(currentState.email, currentState.password)

                _state.update {
                    when (result) {
                        is Result.Success -> {
                            it.copy(isLoading = false, isLoginSuccess = true)
                        }

                        is Result.Error -> {
                            // TODO: Handle login error (e.g. show toast or update state with error message)
                            it.copy(isLoading = false, isLoginSuccess = false)
                        }
                    }
                }
            }
        }
    }
}
