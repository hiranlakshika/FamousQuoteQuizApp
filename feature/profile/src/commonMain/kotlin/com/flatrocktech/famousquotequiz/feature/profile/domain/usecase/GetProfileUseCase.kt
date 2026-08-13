package com.flatrocktech.famousquotequiz.feature.profile.domain.usecase

import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile
import com.flatrocktech.famousquotequiz.feature.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(): UserProfile {
        return repository.getUserProfile()
    }
}
