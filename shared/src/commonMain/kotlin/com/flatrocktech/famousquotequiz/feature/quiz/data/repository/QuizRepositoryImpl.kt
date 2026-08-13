package com.flatrocktech.famousquotequiz.feature.quiz.data.repository

import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Quote
import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class QuizRepositoryImpl : QuizRepository {
    private val _restartFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override fun getQuotes(): List<Quote> {
        // TODO: Fetch quotes from backend when ready
        return listOf(
            Quote("The only thing we have to fear is fear itself.", "Franklin D. Roosevelt"),
            Quote(
                "I have a dream that my four little children will one day live in a nation where they will not be judged by the color of their skin but by the content of their character.",
                "Martin Luther King Jr."
            ),
            Quote("To be, or not to be, that is the question.", "William Shakespeare"),
            Quote(
                "Ask not what your country can do for you – ask what you can do for your country.",
                "John F. Kennedy"
            ),
            Quote("The unexamined life is not worth living.", "Socrates"),
            Quote("I think, therefore I am.", "René Descartes"),
            Quote("Be the change that you wish to see in the world.", "Mahatma Gandhi"),
            Quote(
                "Success is not final, failure is not fatal: it is the courage to continue that counts.",
                "Winston Churchill"
            ),
            Quote("Imagination is more important than knowledge.", "Albert Einstein"),
            Quote("The journey of a thousand miles begins with one step.", "Lao Tzu")
        )
    }

    override fun restartQuiz() {
        _restartFlow.tryEmit(Unit)
    }

    override fun onRestartQuiz(): Flow<Unit> {
        return _restartFlow.asSharedFlow()
    }
}
