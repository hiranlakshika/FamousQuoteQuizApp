package com.flatrocktech.famousquotequiz.core.di

import com.flatrocktech.famousquotequiz.core.data.HttpClientFactory
import com.flatrocktech.famousquotequiz.core.data.TokenStorage
import com.flatrocktech.famousquotequiz.core.data.logging.KermitLoggerImpl
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val coreCommonModule = module {
    single { TokenStorage() }
    single { KermitLoggerImpl() } bind AppLogger::class
    single { HttpClientFactory.create(get(), get(), get()) }
}