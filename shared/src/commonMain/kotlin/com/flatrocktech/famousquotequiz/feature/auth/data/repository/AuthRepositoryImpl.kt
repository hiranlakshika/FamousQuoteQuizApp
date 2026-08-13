package com.flatrocktech.famousquotequiz.feature.auth.data.repository

import com.flatrocktech.famousquotequiz.core.data.util.safeCall
import com.flatrocktech.famousquotequiz.core.domain.EmptyResult
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.core.domain.map
import com.flatrocktech.famousquotequiz.feature.auth.data.mapper.toDomain
import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.LoginResponseDto
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.delay

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.NetworkError> {
        return safeCall<LoginResponseDto> {
            httpClient.post("auth/login") {
                setBody(mapOf("email" to email, "password" to password))
            }
        }.map { it.toDomain() }
    }

    override suspend fun logout(): EmptyResult<DataError.NetworkError> {
        // TODO: Implement actual backend logout using httpClient when ready
        // return safeCall<Unit> {
        //     httpClient.post("auth/logout")
        // }

        delay(1000) // Simulate network delay
        return Result.Success(Unit)
    }
}
