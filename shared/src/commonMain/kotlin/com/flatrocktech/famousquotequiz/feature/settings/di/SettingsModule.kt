package com.flatrocktech.famousquotequiz.feature.settings.di

import com.flatrocktech.famousquotequiz.feature.settings.data.repository.SettingsRepositoryImpl
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetSettingsUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.UpdateSettingsUseCase
import com.flatrocktech.famousquotequiz.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsRepositoryImpl() } bind SettingsRepository::class
    factoryOf(::GetSettingsUseCase)
    factoryOf(::UpdateSettingsUseCase)
    viewModelOf(::SettingsViewModel)
}
