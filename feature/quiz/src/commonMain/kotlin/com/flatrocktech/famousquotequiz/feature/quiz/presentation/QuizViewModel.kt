package com.flatrocktech.famousquotequiz.feature.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.feature.quiz.domain.QuizEventBus
import com.flatrocktech.famousquotequiz.feature.quiz.domain.model.Question
import com.flatrocktech.famousquotequiz.feature.quiz.domain.repository.QuizRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetQuizModeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val getQuizModeUseCase: GetQuizModeUseCase,
    private val quizEventBus: QuizEventBus
) : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state = _state.asStateFlow()

    private var nextQuestion: Question? = null

    init {
        quizEventBus.events
            .onEach {
                restartQuiz()
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            val settings = getQuizModeUseCase()
            _state.update { it.copy(quizMode = settings.quizMode) }
        }
    }

    private fun startNewSession() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = quizRepository.startQuizSession(_state.value.quizMode)) {
                is Result.Success -> {
                    val session = result.data
                    _state.update {
                        it.copy(
                            isLoading = false,
                            sessionId = session.sessionId,
                            totalQuestions = session.totalQuestions
                        )
                    }
                    session.currentQuestion?.let { showQuestion(it) }
                }

                is Result.Error -> {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun showQuestion(question: Question) {
        _state.update {
            it.copy(
                currentQuestion = question.questionNumber,
                totalQuestions = question.totalQuestions,
                quoteText = question.text,
                proposedAuthor = question.proposedAuthor,
                choices = question.options,
                selectedChoiceIndex = null,
                correctChoiceIndex = null,
                isAnswerSubmitted = false,
                isCorrect = null
            )
        }
    }

    fun onIntent(intent: QuizIntent) {
        when (intent) {
            QuizIntent.OnStartQuiz -> {
                startNewSession()
            }

            is QuizIntent.OnChoiceSelected -> {
                if (!_state.value.isAnswerSubmitted) {
                    _state.update { it.copy(selectedChoiceIndex = intent.index) }
                }
            }

            QuizIntent.OnSubmitAnswer -> {
                val selected = _state.value.selectedChoiceIndex ?: return
                val sessionId = _state.value.sessionId ?: return
                val answer = _state.value.choices[selected]

                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    when (val result = quizRepository.submitAnswer(sessionId, answer)) {
                        is Result.Success -> {
                            val answerResult = result.data
                            val correctIndex =
                                _state.value.choices.indexOf(answerResult.correctAuthor)
                                    .takeIf { it != -1 }
                                    ?: if (answerResult.isCorrect) selected else (1 - selected)

                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isAnswerSubmitted = true,
                                    isCorrect = answerResult.isCorrect,
                                    correctChoiceIndex = correctIndex,
                                    correctAnswerExplanation = answerResult.message,
                                    correctAnswersCount = if (answerResult.isCorrect) it.correctAnswersCount + 1 else it.correctAnswersCount,
                                    isQuizFinished = answerResult.sessionCompleted
                                )
                            }
                            nextQuestion = answerResult.nextQuestion
                        }

                        is Result.Error -> {
                            _state.update { it.copy(isLoading = false) }
                        }
                    }
                }
            }

            QuizIntent.OnNextQuestion -> {
                nextQuestion?.let {
                    showQuestion(it)
                    nextQuestion = null
                } ?: run {
                    _state.update { it.copy(isQuizFinished = true) }
                }
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
            _state.update { QuizState(quizMode = settings.quizMode) }
            startNewSession()
        }
    }
}
