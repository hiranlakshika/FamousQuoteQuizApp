package com.flatrocktech.famousquotequiz.feature.settings.domain.usecase

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository

class GetQuizModeUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(): Settings {
        return repository.getSettings()
    }
}
