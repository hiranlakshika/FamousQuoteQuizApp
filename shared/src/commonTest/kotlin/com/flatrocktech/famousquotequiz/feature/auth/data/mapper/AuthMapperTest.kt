package com.flatrocktech.famousquotequiz.feature.auth.data.mapper

import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.LoginResponseDto
import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.UserDto
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthMapperTest {

    @Test
    fun `LoginResponseDto toDomain maps correctly`() {
        val dto = LoginResponseDto(
            token = "token123",
            expiresAt = "2026-09-12T09:58:24.424591Z",
            refreshToken = "refresh123",
            refreshTokenExpiresAt = "2026-10-12T09:58:24.424591Z",
            user = UserDto(
                id = 1,
                email = "demo@quiz.com",
                displayName = "Demo User",
                memberSince = "2026-08-11T19:09:50.140128Z"
            )
        )

        val domain = dto.toDomain()

        assertEquals(dto.token, domain.token)
        assertEquals(dto.expiresAt, domain.expiresAt)
        assertEquals(dto.refreshToken, domain.refreshToken)
        assertEquals(dto.refreshTokenExpiresAt, domain.refreshTokenExpiresAt)
        assertEquals(dto.user.id, domain.user.id)
        assertEquals(dto.user.email, domain.user.email)
        assertEquals(dto.user.displayName, domain.user.displayName)
        assertEquals(dto.user.memberSince, domain.user.memberSince)
    }
}
