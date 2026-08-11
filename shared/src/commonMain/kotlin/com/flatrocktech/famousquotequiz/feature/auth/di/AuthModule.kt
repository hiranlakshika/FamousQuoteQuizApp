package com.flatrocktech.famousquotequiz.feature.auth.di

import com.flatrocktech.famousquotequiz.feature.auth.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel() }
}
