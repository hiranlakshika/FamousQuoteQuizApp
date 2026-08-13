package com.flatrocktech.famousquotequiz.core.di

import com.flatrocktech.famousquotequiz.core.data.SettingsSessionStorage
import com.flatrocktech.famousquotequiz.core.data.logging.KermitLoggerImpl
import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import org.koin.dsl.bind
import org.koin.dsl.module

val coreCommonModule = module {
    single<SessionStorage> { SettingsSessionStorage() }
    single { KermitLoggerImpl() } bind AppLogger::class
}
