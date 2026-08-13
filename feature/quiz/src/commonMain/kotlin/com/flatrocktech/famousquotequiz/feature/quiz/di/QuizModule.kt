package com.flatrocktech.famousquotequiz.feature.quiz.di

import com.flatrocktech.famousquotequiz.feature.quiz.data.repository.QuizRepositoryImpl
import com.flatrocktech.famousquotequiz.feature.quiz.domain.QuizEventBus
import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository
import com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase.RestartQuizUseCase
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.QuizViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val quizModule = module {
    single { QuizEventBus() }
    single { QuizRepositoryImpl(get(), get()) } bind QuizRepository::class
    factoryOf(::RestartQuizUseCase)
    viewModelOf(::QuizViewModel)
}
