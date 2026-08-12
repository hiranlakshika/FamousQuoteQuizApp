package com.flatrocktech.famousquotequiz.feature.settings.domain.usecase

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSettingsUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Settings> {
        return repository.getSettings()
    }
}
