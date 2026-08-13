package com.flatrocktech.famousquotequiz.core.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class TokenStorage(private val settings: Settings = Settings()) {
    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_DISPLAY_NAME = "user_display_name"
    }

    fun saveToken(token: String) {
        settings[KEY_TOKEN] = token
    }

    fun getToken(): String? {
        return settings.getStringOrNull(KEY_TOKEN)
    }

    fun saveUser(email: String, displayName: String) {
        settings[KEY_EMAIL] = email
        settings[KEY_DISPLAY_NAME] = displayName
    }

    fun getEmail(): String? {
        return settings.getStringOrNull(KEY_EMAIL)
    }

    fun getDisplayName(): String? {
        return settings.getStringOrNull(KEY_DISPLAY_NAME)
    }

    fun clearToken() {
        settings.remove(KEY_TOKEN)
        settings.remove(KEY_EMAIL)
        settings.remove(KEY_DISPLAY_NAME)
    }
}
