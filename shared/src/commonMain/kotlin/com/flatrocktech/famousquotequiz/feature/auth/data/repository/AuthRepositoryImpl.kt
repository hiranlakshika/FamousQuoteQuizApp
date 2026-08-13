package com.flatrocktech.famousquotequiz.feature.auth.data.repository

import com.flatrocktech.famousquotequiz.core.data.util.safeCall
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.core.domain.map
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import com.flatrocktech.famousquotequiz.feature.auth.data.mapper.toDomain
import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.LoginResponseDto
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage,
    private val appLogger: AppLogger
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.NetworkError> {
        return safeCall<LoginResponseDto>(appLogger) {
            httpClient.post("auth/login") {
                setBody(mapOf("email" to email, "password" to password))
            }
        }.map { dto ->
            sessionStorage.saveToken(dto.token)
            sessionStorage.saveUser(dto.user.email, dto.user.displayName)
            dto.toDomain()
        }
    }

    override suspend fun logout(): Result<Unit, DataError.NetworkError> {
        sessionStorage.clearSession()
        return Result.Success(Unit)
    }
}
