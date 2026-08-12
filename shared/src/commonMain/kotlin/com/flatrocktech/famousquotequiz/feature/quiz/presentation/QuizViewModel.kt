package com.flatrocktech.famousquotequiz.feature.quiz.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state = _state.asStateFlow()

    fun onIntent(intent: QuizIntent) {
        when (intent) {
            is QuizIntent.OnChoiceSelected -> {
                if (!_state.value.isAnswerSubmitted) {
                    _state.update { it.copy(selectedChoiceIndex = intent.index) }
                }
            }
            QuizIntent.OnSubmitAnswer -> {
                val selected = _state.value.selectedChoiceIndex ?: return
                val correct = _state.value.correctChoiceIndex ?: 1 // default correct = index 1
                _state.update {
                    it.copy(
                        isAnswerSubmitted = true,
                        isCorrect = selected == correct,
                        correctChoiceIndex = correct
                    )
                }
            }
            QuizIntent.OnNextQuestion -> {
                _state.update {
                    it.copy(
                        currentQuestion = (it.currentQuestion % it.totalQuestions) + 1,
                        selectedChoiceIndex = null,
                        correctChoiceIndex = null,
                        isAnswerSubmitted = false,
                        isCorrect = null
                    )
                }
            }
            QuizIntent.OnDismissResult -> {
                _state.update {
                    it.copy(isAnswerSubmitted = false, isCorrect = null)
                }
            }
        }
    }
}
