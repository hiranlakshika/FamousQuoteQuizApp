package com.flatrocktech.famousquotequiz.feature.settings.domain.repository

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings

class FakeSettingsRepository : SettingsRepository {
    var settings = Settings(quizMode = QuizMode.BINARY)

    override suspend fun getSettings(): Settings {
        return settings
    }

    override suspend fun updateSettings(settings: Settings) {
        this.settings = settings
    }
}
