package com.flatrocktech.famousquotequiz.feature.auth.domain.repository

import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class FakeAuthRepository : AuthRepository {
    var loginResult: Result<AuthInfo, DataError.NetworkError> =
        Result.Error(DataError.NetworkError.UNKNOWN)
    var logoutResult: Result<Unit, DataError.NetworkError> = Result.Success(Unit)
    var logoutDelay: Long = 0

    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthInfo, DataError.NetworkError> {
        return loginResult
    }

    override suspend fun logout(): Result<Unit, DataError.NetworkError> {
        if (logoutDelay > 0) delay(logoutDelay.milliseconds)
        return logoutResult
    }
}
