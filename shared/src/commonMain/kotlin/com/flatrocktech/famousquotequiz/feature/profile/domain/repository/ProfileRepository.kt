package com.flatrocktech.famousquotequiz.feature.profile.domain.repository

import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile

interface ProfileRepository {
    fun getUserProfile(): UserProfile
}
