package com.flatrocktech.famousquotequiz.feature.settings.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.flatrocktech.famousquotequiz.core.domain.FakeSessionStorage
import com.flatrocktech.famousquotequiz.core.domain.util.FakeAppLogger
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SettingsRepositoryImplTest {

    private lateinit var sessionStorage: FakeSessionStorage
    private lateinit var appLogger: FakeAppLogger
    private lateinit var repository: SettingsRepositoryImpl

    @BeforeTest
    fun setup() {
        sessionStorage = FakeSessionStorage()
        appLogger = FakeAppLogger()
        repository = SettingsRepositoryImpl(sessionStorage, appLogger)
    }

    @Test
    fun `getSettings should return settings with mode from session storage`() = runTest {
        // Given
        sessionStorage.savedMode = QuizMode.MULTIPLE_CHOICE.name

        // When
        val settings = repository.getSettings()

        // Then
        assertThat(settings.quizMode).isEqualTo(QuizMode.MULTIPLE_CHOICE)
    }

    @Test
    fun `getSettings should return binary mode when session storage is empty`() = runTest {
        // Given
        sessionStorage.savedMode = null

        // When
        val settings = repository.getSettings()

        // Then
        assertThat(settings.quizMode).isEqualTo(QuizMode.BINARY)
    }

    @Test
    fun `getSettings should return binary mode and log error when session storage has invalid mode`() =
        runTest {
            // Given
            sessionStorage.savedMode = "INVALID_MODE"

            // When
            val settings = repository.getSettings()

            // Then
            assertThat(settings.quizMode).isEqualTo(QuizMode.BINARY)
            assertThat(appLogger.errorLogged).isTrue()
        }

    @Test
    fun `updateSettings should save mode to session storage`() = runTest {
        // Given
        val settings = Settings(quizMode = QuizMode.MULTIPLE_CHOICE)

        // When
        repository.updateSettings(settings)

        // Then
        assertThat(sessionStorage.savedMode).isEqualTo(QuizMode.MULTIPLE_CHOICE.name)
    }
}
