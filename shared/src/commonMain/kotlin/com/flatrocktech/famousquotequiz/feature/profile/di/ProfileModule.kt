package com.flatrocktech.famousquotequiz.feature.profile.di

import com.flatrocktech.famousquotequiz.feature.profile.data.repository.ProfileRepositoryImpl
import com.flatrocktech.famousquotequiz.feature.profile.domain.repository.ProfileRepository
import com.flatrocktech.famousquotequiz.feature.profile.domain.usecase.GetProfileUseCase
import com.flatrocktech.famousquotequiz.feature.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule = module {
    factoryOf(::ProfileRepositoryImpl) bind ProfileRepository::class
    factoryOf(::GetProfileUseCase)
    viewModelOf(::ProfileViewModel)
}
