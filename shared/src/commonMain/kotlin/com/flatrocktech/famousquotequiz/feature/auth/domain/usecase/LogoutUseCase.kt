package com.flatrocktech.famousquotequiz.feature.auth.domain.usecase

import com.flatrocktech.famousquotequiz.core.domain.EmptyResult
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): EmptyResult<DataError.NetworkError> {
        return repository.logout()
    }
}
