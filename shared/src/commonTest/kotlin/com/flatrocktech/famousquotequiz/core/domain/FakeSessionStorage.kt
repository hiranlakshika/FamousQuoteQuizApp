package com.flatrocktech.famousquotequiz.core.domain

class FakeSessionStorage : SessionStorage {
    var savedToken: String? = null
    var savedEmail: String? = null
    var savedDisplayName: String? = null
    var savedMode: String? = null

    override fun saveToken(token: String) {
        savedToken = token
    }

    override fun getToken(): String? = savedToken

    override fun saveUser(email: String, displayName: String) {
        savedEmail = email
        savedDisplayName = displayName
    }

    override fun getEmail(): String? = savedEmail

    override fun getDisplayName(): String? = savedDisplayName

    override fun saveQuizMode(mode: String) {
        savedMode = mode
    }

    override fun getQuizMode(): String? = savedMode

    override fun clearSession() {
        savedToken = null
        savedEmail = null
        savedDisplayName = null
        savedMode = null
    }
}
