package com.flatrocktech.famousquotequiz.feature.profile.presentation

sealed interface ProfileIntent {
    data object OnLogoutClicked : ProfileIntent
}
