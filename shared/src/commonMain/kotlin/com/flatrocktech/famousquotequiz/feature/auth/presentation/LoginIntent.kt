package com.flatrocktech.famousquotequiz.feature.auth.presentation

sealed interface LoginIntent {
    data class OnEmailChanged(val email: String) : LoginIntent
    data class OnPasswordChanged(val password: String) : LoginIntent
    data object OnLoginClicked : LoginIntent
}
