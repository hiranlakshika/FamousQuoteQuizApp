package com.flatrocktech.famousquotequiz.feature.quiz.domain.repository

import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.AnswerResult
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.QuizSession
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode

interface QuizRepository {
    suspend fun startQuizSession(mode: QuizMode): Result<QuizSession, DataError.NetworkError>
    suspend fun submitAnswer(
        sessionId: String,
        answer: String
    ): Result<AnswerResult, DataError.NetworkError>
}
