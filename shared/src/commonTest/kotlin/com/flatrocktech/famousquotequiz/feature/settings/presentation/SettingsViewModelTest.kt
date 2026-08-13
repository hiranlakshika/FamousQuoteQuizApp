package com.flatrocktech.famousquotequiz.feature.settings.presentation

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.flatrocktech.famousquotequiz.feature.quiz.domain.QuizEventBus
import com.flatrocktech.famousquotequiz.feature.quiz.domain.usecase.RestartQuizUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.model.QuizMode
import com.flatrocktech.famousquotequiz.feature.settings.domain.repository.FakeSettingsRepository
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.GetQuizModeUseCase
import com.flatrocktech.famousquotequiz.feature.settings.domain.usecase.UpdateQuizModeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var repository: FakeSettingsRepository
    private lateinit var quizEventBus: QuizEventBus

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeSettingsRepository()
        quizEventBus = QuizEventBus()

        viewModel = SettingsViewModel(
            getQuizModeUseCase = GetQuizModeUseCase(repository),
            updateQuizModeUseCase = UpdateQuizModeUseCase(repository),
            restartQuizUseCase = RestartQuizUseCase(quizEventBus)
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initially should load settings from repository`() = runTest {
        // Given
        repository.settings = repository.settings.copy(quizMode = QuizMode.MULTIPLE_CHOICE)

        // When (ViewModel is already created in setup, but we need to wait for init block)
        // Re-creating to capture the specific state change after Given
        viewModel = SettingsViewModel(
            getQuizModeUseCase = GetQuizModeUseCase(repository),
            updateQuizModeUseCase = UpdateQuizModeUseCase(repository),
            restartQuizUseCase = RestartQuizUseCase(quizEventBus)
        )
        advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.quizMode).isEqualTo(QuizMode.MULTIPLE_CHOICE)
    }

    @Test
    fun `OnQuizModeChanged should update repository and state and restart quiz`() = runTest {
        // Given
        var restartTriggered = false
        val job = launch {
            quizEventBus.events.first()
            restartTriggered = true
        }

        // When
        viewModel.onIntent(SettingsIntent.OnQuizModeChanged(QuizMode.MULTIPLE_CHOICE))
        advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.quizMode).isEqualTo(QuizMode.MULTIPLE_CHOICE)
        assertThat(repository.settings.quizMode).isEqualTo(QuizMode.MULTIPLE_CHOICE)
        assertThat(restartTriggered).isEqualTo(true)

        job.cancel()
    }
}
