package com.flatrocktech.famousquotequiz.feature.auth.di

import com.flatrocktech.famousquotequiz.feature.auth.data.repository.AuthRepositoryImpl
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.AuthRepository
import com.flatrocktech.famousquotequiz.feature.auth.domain.usecase.LoginUseCase
import com.flatrocktech.famousquotequiz.feature.auth.domain.usecase.LogoutUseCase
import com.flatrocktech.famousquotequiz.feature.auth.presentation.LoginViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    single { AuthRepositoryImpl(get()) } bind AuthRepository::class
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    viewModelOf(::LoginViewModel)
}
