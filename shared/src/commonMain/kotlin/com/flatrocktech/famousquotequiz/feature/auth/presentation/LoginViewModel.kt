package com.flatrocktech.famousquotequiz.feature.auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
}