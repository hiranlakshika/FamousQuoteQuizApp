package com.flatrocktech.famousquotequiz.feature.profile.di

import com.flatrocktech.famousquotequiz.feature.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
}
