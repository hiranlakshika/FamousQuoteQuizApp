package com.flatrocktech.famousquotequiz.core.di

import org.koin.core.component.KoinComponent

class KoinHelper: KoinComponent {
    fun initKoin() {
        com.flatrocktech.famousquotequiz.core.di.initKoin()
    }
}
