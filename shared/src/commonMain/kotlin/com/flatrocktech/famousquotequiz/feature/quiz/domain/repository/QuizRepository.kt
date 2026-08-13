package com.flatrocktech.famousquotequiz.feature.quiz.domain.repository

import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Quote

import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuotes(): List<Quote>
    fun restartQuiz()
    fun onRestartQuiz(): Flow<Unit>
}
