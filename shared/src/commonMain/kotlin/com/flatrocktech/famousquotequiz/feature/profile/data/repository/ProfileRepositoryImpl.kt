package com.flatrocktech.famousquotequiz.feature.profile.data.repository

import com.flatrocktech.famousquotequiz.core.data.TokenStorage
import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile
import com.flatrocktech.famousquotequiz.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val tokenStorage: TokenStorage
) : ProfileRepository {
    override fun getUserProfile(): UserProfile {
        return UserProfile(
            name = tokenStorage.getDisplayName() ?: "",
            email = tokenStorage.getEmail() ?: ""
        )
    }
}
