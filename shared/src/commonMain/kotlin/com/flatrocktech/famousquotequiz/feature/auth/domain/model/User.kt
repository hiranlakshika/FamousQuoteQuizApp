package com.flatrocktech.famousquotequiz.feature.auth.domain.model

data class User(
    val id: Int,
    val email: String,
    val displayName: String,
    val memberSince: String
)
