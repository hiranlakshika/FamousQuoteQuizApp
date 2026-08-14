package com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String,
    val expiresAt: String,
    val refreshToken: String,
    val refreshTokenExpiresAt: String,
    val user: UserDto,
)

@Serializable
data class UserDto(
    val id: Int,
    val email: String,
    val displayName: String,
    val memberSince: String
)
