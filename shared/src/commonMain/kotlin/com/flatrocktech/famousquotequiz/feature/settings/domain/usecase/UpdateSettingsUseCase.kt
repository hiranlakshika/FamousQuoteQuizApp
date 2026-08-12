package com.flatrocktech.famousquotequiz.feature.settings.domain.usecase

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository

class UpdateSettingsUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(settings: Settings) {
        repository.updateSettings(settings)
    }
}
