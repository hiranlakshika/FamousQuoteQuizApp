package com.flatrocktech.famousquotequiz.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Login : Route

    @Serializable
    data object Quiz : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data object Profile : Route
}