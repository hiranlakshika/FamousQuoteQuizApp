package com.flatrocktech.famousquotequiz.feature.profile.presentation

data class ProfileState(
    val isLoading: Boolean = false,
    val isLogoutSuccess: Boolean = false,
    val name: String = "Jane Scholar",
    val email: String = "jane.scholar@example.com",
    val quizzesTaken: Int = 342,
    val avgScore: Int = 89
)
