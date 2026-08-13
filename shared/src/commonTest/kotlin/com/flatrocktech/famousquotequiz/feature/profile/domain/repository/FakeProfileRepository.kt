package com.flatrocktech.famousquotequiz.feature.profile.domain.repository

import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile

class FakeProfileRepository : ProfileRepository {
    var profile = UserProfile(name = "Test User", email = "test@example.com")

    override fun getUserProfile(): UserProfile {
        return profile
    }
}
