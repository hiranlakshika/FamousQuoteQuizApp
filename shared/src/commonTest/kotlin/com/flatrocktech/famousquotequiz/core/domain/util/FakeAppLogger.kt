package com.flatrocktech.famousquotequiz.core.domain.util

class FakeAppLogger : AppLogger {
    var errorLogged = false
    var lastError: Throwable? = null
    var lastTag: String? = null

    override fun debug(tag: String?, message: () -> String) {}
    override fun info(tag: String?, message: () -> String) {}
    override fun warn(tag: String?, message: () -> String) {}
    override fun error(tag: String?, throwable: Throwable?, message: () -> String) {
        errorLogged = true
        lastError = throwable
        lastTag = tag
    }
}
