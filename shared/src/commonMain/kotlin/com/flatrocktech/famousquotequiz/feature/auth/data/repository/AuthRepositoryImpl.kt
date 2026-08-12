package com.flatrocktech.famousquotequiz.feature.auth.data.repository

import com.flatrocktech.famousquotequiz.core.domain.EmptyResult
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.NetworkError> {
        // TODO: Implement actual backend login using httpClient when ready
        // return safeCall<AuthInfo> {
        //     httpClient.post("auth/login") {
        //         setBody(mapOf("email" to email, "password" to password))
        //     }
        // }

        delay(1500) // Simulate network delay

        return if (email == "test@example.com" && password == "password123") {
            Result.Success(
                AuthInfo(
                    token = "fake-jwt-token",
                    userId = "user-123"
                )
            )
        } else {
            Result.Error(DataError.NetworkError.UNKNOWN) // Or a more specific error
        }
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
