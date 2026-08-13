package com.flatrocktech.famousquotequiz.feature.settings.domain.repository

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings

interface SettingsRepository {
    suspend fun getSettings(): Settings
    suspend fun updateSettings(settings: Settings)
}
