package com.flatrocktech.famousquotequiz.feature.quiz.data.mapper

import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.AnswerResponseDto
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.QuestionDto
import com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto.QuizSessionResponseDto
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.AnswerResult
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Question
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.QuizSession

fun QuestionDto.toDomain(): Question {
    return Question(
        questionNumber = questionNumber,
        totalQuestions = totalQuestions,
        text = quote,
        proposedAuthor = proposedAuthor,
        options = options
    )
}

fun QuizSessionResponseDto.toDomain(): QuizSession {
    return QuizSession(
        sessionId = sessionId,
        totalQuestions = totalQuestions,
        currentQuestion = currentQuestion?.toDomain()
    )
}

fun AnswerResponseDto.toDomain(): AnswerResult {
    return AnswerResult(
        isCorrect = correct,
        correctAuthor = correctAuthor,
        message = message,
        sessionCompleted = sessionCompleted,
        nextQuestion = nextQuestion?.toDomain()
    )
}
