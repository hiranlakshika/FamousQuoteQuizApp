package com.flatrocktech.famousquotequiz.feature.quiz.data.repository

import com.flatrocktech.famousquotequiz.core.data.util.safeCall
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.core.domain.map
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import com.flatrocktech.famousquotequiz.feature.quiz.data.mapper.toDomain
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.AnswerRequestDto
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.AnswerResponseDto
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.QuizSessionRequestDto
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.QuizSessionResponseDto
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.AnswerResult
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.QuizSession
import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class QuizRepositoryImpl(
    private val httpClient: HttpClient,
    private val appLogger: AppLogger
) : QuizRepository {

    override suspend fun startQuizSession(mode: QuizMode): Result<QuizSession, DataError.NetworkError> {
        return safeCall<QuizSessionResponseDto>(appLogger) {
            httpClient.post("quiz/sessions") {
                setBody(QuizSessionRequestDto(mode.name))
            }
        }.map { it.toDomain() }
    }

    override suspend fun submitAnswer(
        sessionId: String,
        answer: String
    ): Result<AnswerResult, DataError.NetworkError> {
        return safeCall<AnswerResponseDto>(appLogger) {
            httpClient.post("quiz/sessions/$sessionId/answers") {
                setBody(AnswerRequestDto(answer))
            }
        }.map { it.toDomain() }
    }
}
