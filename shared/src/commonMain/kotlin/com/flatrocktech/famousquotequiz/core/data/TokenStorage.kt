package com.flatrocktech.famousquotequiz.core.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class TokenStorage(private val settings: Settings = Settings()) {
    companion object {
        private const val KEY_TOKEN = "auth_token"
    }

    fun saveToken(token: String) {
        settings[KEY_TOKEN] = token
    }

    fun getToken(): String? {
        return settings.getStringOrNull(KEY_TOKEN)
    }

    fun clearToken() {
        settings.remove(KEY_TOKEN)
    }
}
