package com.flatrocktech.famousquotequiz.feature.auth.domain.model

data class AuthInfo(
    val token: String,
    val expiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String,
    val user: User
)
