package com.flatrocktech.famousquotequiz.core.domain

interface SessionStorage {
    fun saveToken(token: String)
    fun getToken(): String?
    fun saveUser(email: String, displayName: String)
    fun getEmail(): String?
    fun getDisplayName(): String?
    fun clearSession()
}
