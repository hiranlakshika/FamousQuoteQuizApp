package com.flatrocktech.famousquotequiz.feature.auth.domain.model

data class AuthInfo(
    val token: String,
    val expiresAt: String,
    val user: User
)
