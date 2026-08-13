package com.flatrocktech.famousquotequiz.feature.quiz.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class QuizEventBus {
    private val _events = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val events: Flow<Unit> = _events.asSharedFlow()

    fun restartQuiz() {
        _events.tryEmit(Unit)
    }
}
