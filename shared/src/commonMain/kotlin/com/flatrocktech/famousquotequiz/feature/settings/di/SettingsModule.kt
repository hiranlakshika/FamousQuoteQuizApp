package com.flatrocktech.famousquotequiz.feature.settings.di

import com.flatrocktech.famousquotequiz.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel() }
}
