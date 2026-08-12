package com.flatrocktech.famousquotequiz.feature.profile.di

import com.flatrocktech.famousquotequiz.feature.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel() }
}
