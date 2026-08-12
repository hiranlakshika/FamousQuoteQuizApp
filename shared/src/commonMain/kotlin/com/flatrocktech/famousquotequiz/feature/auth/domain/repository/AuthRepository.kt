package com.flatrocktech.famousquotequiz.feature.auth.domain.repository

import com.flatrocktech.famousquotequiz.core.domain.EmptyResult
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.feature.auth.domain.model.AuthInfo

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<AuthInfo, DataError.NetworkError>
    suspend fun logout(): EmptyResult<DataError.NetworkError>
}
