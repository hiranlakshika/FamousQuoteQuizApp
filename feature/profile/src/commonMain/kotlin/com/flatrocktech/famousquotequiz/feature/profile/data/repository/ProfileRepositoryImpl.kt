package com.flatrocktech.famousquotequiz.feature.profile.data.repository

import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile
import com.flatrocktech.famousquotequiz.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val sessionStorage: SessionStorage
) : ProfileRepository {
    override fun getUserProfile(): UserProfile {
        return UserProfile(
            name = sessionStorage.getDisplayName() ?: "",
            email = sessionStorage.getEmail() ?: ""
        )
    }
}
