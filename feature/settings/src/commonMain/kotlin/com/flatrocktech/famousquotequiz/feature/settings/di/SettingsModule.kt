package com.flatrocktech.famousquotequiz.feature.settings.di

import com.flatrocktech.famousquotequiz.feature.settings.data.repository.SettingsRepositoryImpl
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.SettingsRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetQuizModeUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.UpdateQuizModeUseCase
import com.flatrocktech.famousquotequiz.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsRepositoryImpl(get(), get()) } bind SettingsRepository::class
    factoryOf(::GetQuizModeUseCase)
    factoryOf(::UpdateQuizModeUseCase)
    viewModelOf(::SettingsViewModel)
}
