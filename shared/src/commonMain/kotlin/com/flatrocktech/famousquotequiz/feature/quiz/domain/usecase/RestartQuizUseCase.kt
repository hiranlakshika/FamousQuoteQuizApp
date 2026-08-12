package com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase

import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository

class RestartQuizUseCase(
    private val repository: QuizRepository
) {
    operator fun invoke() {
        repository.restartQuiz()
    }
}
