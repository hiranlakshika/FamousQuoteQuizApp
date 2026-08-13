package com.flatrocktech.famousquotequiz.feature.quiz.domain.model

data class Question(
    val questionNumber: Int,
    val totalQuestions: Int,
    val text: String,
    val proposedAuthor: String?,
    val options: List<String>
)

data class QuizSession(
    val sessionId: String,
    val totalQuestions: Int,
    val currentQuestion: Question?
)

data class AnswerResult(
    val isCorrect: Boolean,
    val correctAuthor: String,
    val message: String,
    val sessionCompleted: Boolean,
    val nextQuestion: Question?
)
