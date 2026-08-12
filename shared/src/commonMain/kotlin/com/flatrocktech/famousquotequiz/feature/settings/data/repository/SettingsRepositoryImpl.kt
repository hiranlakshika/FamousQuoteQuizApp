package com.flatrocktech.famousquotequiz.feature.settings.data.repository

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsRepositoryImpl : SettingsRepository {
    private val _settings = MutableStateFlow(Settings())

    override fun getSettings(): Flow<Settings> {
        return _settings.asStateFlow()
    }

    override suspend fun updateSettings(settings: Settings) {
        // TODO: Persist settings to local storage (e.g. DataStore or KMP-Settings) when ready
        _settings.update { settings }
    }
}
