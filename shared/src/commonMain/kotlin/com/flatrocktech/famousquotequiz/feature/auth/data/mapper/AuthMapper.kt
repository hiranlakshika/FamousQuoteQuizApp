package com.flatrocktech.famousquotequiz.feature.auth.data.mapper

import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.LoginResponseDto
import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.UserDto
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.User

fun LoginResponseDto.toDomain(): AuthInfo {
    return AuthInfo(
        token = token,
        expiresAt = expiresAt,
        refreshToken = refreshToken,
        refreshTokenExpiresAt = refreshTokenExpiresAt,
        user = user.toDomain(),
    )
}

fun UserDto.toDomain(): User {
    return User(
        id = id,
        email = email,
        displayName = displayName,
        memberSince = memberSince
    )
}
