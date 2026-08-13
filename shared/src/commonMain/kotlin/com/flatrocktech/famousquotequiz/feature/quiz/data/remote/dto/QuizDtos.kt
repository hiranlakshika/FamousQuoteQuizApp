package com.flatrocktech.famousquotequiz.feature.quiz.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionRequestDto(
    val mode: String
)

@Serializable
data class QuestionDto(
    val questionNumber: Int,
    val totalQuestions: Int,
    val quote: String,
    val proposedAuthor: String? = null,
    val options: List<String>
)

@Serializable
data class QuizSessionResponseDto(
    val sessionId: String,
    val mode: String,
    val totalQuestions: Int,
    val answeredQuestions: Int,
    val completed: Boolean,
    val currentQuestion: QuestionDto?
)

@Serializable
data class AnswerRequestDto(
    val answer: String
)

@Serializable
data class AnswerResponseDto(
    val correct: Boolean,
    val correctAuthor: String,
    val message: String,
    val sessionCompleted: Boolean,
    val nextQuestion: QuestionDto?
)
