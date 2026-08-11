package com.flatrocktech.famousquotequiz

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.flatrocktech.famousquotequiz.app.App
import com.flatrocktech.famousquotequiz.core.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "FamousQuoteQuiz",
        ) {
            App()
        }
    }
}