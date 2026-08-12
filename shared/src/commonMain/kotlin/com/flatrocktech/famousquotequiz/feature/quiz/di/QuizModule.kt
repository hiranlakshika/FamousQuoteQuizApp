package com.flatrocktech.famousquotequiz.feature.quiz.di

import com.flatrocktech.famousquotequiz.feature.quiz.presentation.QuizViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    viewModel { QuizViewModel() }
}
