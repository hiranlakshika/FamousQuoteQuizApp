package com.flatrocktech.famousquotequiz.feature.settings.domain.repository

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<Settings>
    suspend fun updateSettings(settings: Settings)
}
