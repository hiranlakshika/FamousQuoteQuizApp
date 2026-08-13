package com.flatrocktech.famousquotequiz.feature.profile.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.flatrocktech.famousquotequiz.core.domain.FakeSessionStorage
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProfileRepositoryImplTest {

    private lateinit var sessionStorage: FakeSessionStorage
    private lateinit var repository: ProfileRepositoryImpl

    @BeforeTest
    fun setup() {
        sessionStorage = FakeSessionStorage()
        repository = ProfileRepositoryImpl(sessionStorage)
    }

    @Test
    fun `getUserProfile should return profile with data from session storage`() {
        // Given
        val expectedName = "Test User"
        val expectedEmail = "test@example.com"
        sessionStorage.savedDisplayName = expectedName
        sessionStorage.savedEmail = expectedEmail

        // When
        val profile = repository.getUserProfile()

        // Then
        assertThat(profile.name).isEqualTo(expectedName)
        assertThat(profile.email).isEqualTo(expectedEmail)
    }

    @Test
    fun `getUserProfile should return empty strings when session storage is empty`() {
        // Given
        sessionStorage.savedDisplayName = null
        sessionStorage.savedEmail = null

        // When
        val profile = repository.getUserProfile()

        // Then
        assertThat(profile.name).isEqualTo("")
        assertThat(profile.email).isEqualTo("")
    }
}
