package com.flatrocktech.famousquotequiz.feature.settings.data.repository

import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val sessionStorage: SessionStorage,
    private val appLogger: AppLogger
) : SettingsRepository {

    override suspend fun getSettings(): Settings {
        val mode = try {
            QuizMode.valueOf(sessionStorage.getQuizMode() ?: QuizMode.BINARY.name)
        } catch (e: Exception) {
            appLogger.error(
                "SettingsRepository",
                e
            ) { "Failed to parse quiz mode from session storage" }
            QuizMode.BINARY
        }
        return Settings(quizMode = mode)
    }

    override suspend fun updateSettings(settings: Settings) {
        sessionStorage.saveQuizMode(settings.quizMode.name)
    }
}
