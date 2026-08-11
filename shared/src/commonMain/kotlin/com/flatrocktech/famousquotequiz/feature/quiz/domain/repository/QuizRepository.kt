package com.flatrocktech.famousquotequiz.feature.quiz.domain.repository

import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Quote

interface QuizRepository {
    fun getQuotes(): List<Quote>
}
