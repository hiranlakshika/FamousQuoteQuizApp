package com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase

import com.flatrocktech.famousquotequiz.feature.quiz.domain.QuizEventBus

class RestartQuizUseCase(
    private val quizEventBus: QuizEventBus
) {
    operator fun invoke() {
        quizEventBus.restartQuiz()
    }
}
