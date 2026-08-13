package com.flatrocktech.famousquotequiz.core.di

import com.flatrocktech.famousquotequiz.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val coreNetworkModule = module {
    single { HttpClientFactory.create(get(), get(), get()) }
}
