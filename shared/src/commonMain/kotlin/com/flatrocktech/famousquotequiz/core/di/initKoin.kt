package com.flatrocktech.famousquotequiz.core.di

import com.flatrocktech.famousquotequiz.feature.auth.di.authModule
import com.flatrocktech.famousquotequiz.feature.profile.di.profileModule
import com.flatrocktech.famousquotequiz.feature.settings.di.settingsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModule, coreCommonModule, authModule, settingsModule, profileModule)
    }
}