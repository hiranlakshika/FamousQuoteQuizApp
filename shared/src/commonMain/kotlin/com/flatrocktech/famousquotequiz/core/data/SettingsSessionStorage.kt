package com.flatrocktech.famousquotequiz.core.data

import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class SettingsSessionStorage(private val settings: Settings = Settings()) : SessionStorage {
    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_DISPLAY_NAME = "user_display_name"
    }

    override fun saveToken(token: String) {
        settings[KEY_TOKEN] = token
    }

    override fun getToken(): String? {
        return settings.getStringOrNull(KEY_TOKEN)
    }

    override fun saveUser(email: String, displayName: String) {
        settings[KEY_EMAIL] = email
        settings[KEY_DISPLAY_NAME] = displayName
    }

    override fun getEmail(): String? {
        return settings.getStringOrNull(KEY_EMAIL)
    }

    override fun getDisplayName(): String? {
        return settings.getStringOrNull(KEY_DISPLAY_NAME)
    }

    override fun clearSession() {
        settings.remove(KEY_TOKEN)
        settings.remove(KEY_EMAIL)
        settings.remove(KEY_DISPLAY_NAME)
    }
}
