package com.flatrocktech.famousquotequiz.core.data

import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class SettingsSessionStorage(private val settings: Settings = Settings()) : SessionStorage {
    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_DISPLAY_NAME = "user_display_name"
        private const val KEY_QUIZ_MODE = "quiz_mode"
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

    override fun saveQuizMode(mode: String) {
        settings[KEY_QUIZ_MODE] = mode
    }

    override fun getQuizMode(): String? {
        return settings.getStringOrNull(KEY_QUIZ_MODE)
    }

    override fun clearSession() {
        settings.clear()
    }
}
