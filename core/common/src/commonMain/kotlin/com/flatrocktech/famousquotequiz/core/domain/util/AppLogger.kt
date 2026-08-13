package com.flatrocktech.famousquotequiz.core.domain.util

interface AppLogger {
    fun debug(tag: String? = null, message: () -> String)
    fun info(tag: String? = null, message: () -> String)
    fun warn(tag: String? = null, message: () -> String)
    fun error(tag: String? = null, throwable: Throwable? = null, message: () -> String)
}
