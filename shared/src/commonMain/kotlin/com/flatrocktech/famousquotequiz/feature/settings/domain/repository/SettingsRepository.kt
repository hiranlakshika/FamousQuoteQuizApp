package com.flatrocktech.famousquotequiz.feature.settings.domain.repository

import com.flatrocktech.famousquotequiz.feature.settings.domain.model.Settings

interface SettingsRepository {
    fun getSettings(): Settings
}
