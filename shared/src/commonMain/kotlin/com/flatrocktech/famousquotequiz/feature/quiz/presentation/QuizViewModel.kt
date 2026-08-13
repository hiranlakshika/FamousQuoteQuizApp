package com.flatrocktech.famousquotequiz.feature.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Quote
import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetQuizModeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val getQuizModeUseCase: GetQuizModeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state = _state.asStateFlow()

    private var allQuotes = emptyList<Quote>()
    private var quizMode = QuizMode.BINARY

    init {
        quizRepository.onRestartQuiz()
            .onEach {
                restartQuiz()
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            val settings = getQuizModeUseCase()
            quizMode = settings.quizMode
            loadQuotes()
        }
    }

    private fun loadQuotes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            allQuotes = quizRepository.getQuotes().take(10)
            _state.update { it.copy(isLoading = false, totalQuestions = allQuotes.size) }
            showQuestion(0)
        }
    }

    private fun showQuestion(index: Int) {
        if (index >= allQuotes.size) {
            _state.update { it.copy(isQuizFinished = true) }
            return
        }

        val quote = allQuotes[index]
        val currentChoices = generateChoices(quote)
        val correctIndex = currentChoices.indexOf(quote.author)

        _state.update {
            it.copy(
                currentQuestion = index + 1,
                quoteText = quote.text,
                choices = currentChoices,
                correctChoiceIndex = correctIndex,
                selectedChoiceIndex = null,
                isAnswerSubmitted = false,
                isCorrect = null,
                correctAnswerExplanation = "The quote is by ${quote.author}."
            )
        }
    }

    private fun generateChoices(quote: Quote): List<String> {
        val authors = allQuotes.map { it.author }.distinct()
        return when (quizMode) {
            QuizMode.BINARY -> {
                // Yes/No logic might be different, but requirement says "picked the right answer"
                // Usually Binary means 2 choices. Let's provide 2 authors.
                val wrongAuthor =
                    authors.filter { it != quote.author }.shuffled().firstOrNull() ?: "Unknown"
                listOf(quote.author, wrongAuthor).shuffled()
            }

            QuizMode.MULTIPLE_CHOICE -> {
                val wrongAuthors = authors.filter { it != quote.author }.shuffled().take(2)
                (listOf(quote.author) + wrongAuthors).shuffled()
            }
        }
    }

    fun onIntent(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.OnChoiceSelected -> {
                if (!_state.value.isAnswerSubmitted) {
                    _state.update { it.copy(selectedChoiceIndex = intent.index) }
                }
            }
            QuizIntent.OnSubmitAnswer -> {
                val selected = _state.value.selectedChoiceIndex ?: return
                val correct = _state.value.correctChoiceIndex ?: return
                val isCorrect = selected == correct
                
                _state.update {
                    it.copy(
                        isAnswerSubmitted = true,
                        isCorrect = isCorrect,
                        correctAnswersCount = if (isCorrect) it.correctAnswersCount + 1 else it.correctAnswersCount
                    )
                }
            }
            QuizIntent.OnNextQuestion -> {
                showQuestion(_state.value.currentQuestion)
            }
            QuizIntent.OnDismissResult -> {
                _state.update {
                    it.copy(isAnswerSubmitted = false, isCorrect = null)
                }
            }

            QuizIntent.OnRestartQuiz -> {
                restartQuiz()
            }
        }
    }

    private fun restartQuiz() {
        viewModelScope.launch {
            val settings = getQuizModeUseCase()
            quizMode = settings.quizMode
            _state.update { QuizState() }
            loadQuotes()
        }
    }
}
