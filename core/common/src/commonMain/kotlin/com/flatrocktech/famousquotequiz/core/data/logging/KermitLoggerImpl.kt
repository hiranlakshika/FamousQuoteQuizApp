package com.flatrocktech.famousquotequiz.core.data.logging

import co.touchlab.kermit.Logger
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger

class KermitLoggerImpl : AppLogger {
    override fun debug(tag: String?, message: () -> String) {
        val logger = if (tag != null) Logger.withTag(tag) else Logger
        logger.d(message = message)
    }

    override fun info(tag: String?, message: () -> String) {
        val logger = if (tag != null) Logger.withTag(tag) else Logger
        logger.i(message = message)
    }

    override fun warn(tag: String?, message: () -> String) {
        val logger = if (tag != null) Logger.withTag(tag) else Logger
        logger.w(message = message)
    }

    override fun error(tag: String?, throwable: Throwable?, message: () -> String) {
        val logger = if (tag != null) Logger.withTag(tag) else Logger
        logger.e(throwable = throwable, message = message)
    }
}
