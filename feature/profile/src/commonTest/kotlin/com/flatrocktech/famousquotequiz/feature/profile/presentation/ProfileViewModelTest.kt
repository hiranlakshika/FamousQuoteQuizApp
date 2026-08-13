package com.flatrocktech.famousquotequiz.feature.profile.presentation

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.feature.auth.domain.repository.FakeAuthRepository
import com.flatrocktech.famousquotequiz.feature.auth.domain.usecase.LogoutUseCase
import com.flatrocktech.famousquotequiz.feature.profile.domain.model.UserProfile
import com.flatrocktech.famousquotequiz.feature.profile.domain.repository.FakeProfileRepository
import com.flatrocktech.famousquotequiz.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var authRepository: FakeAuthRepository
    private lateinit var profileRepository: FakeProfileRepository

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        authRepository = FakeAuthRepository()
        profileRepository = FakeProfileRepository()

        viewModel = ProfileViewModel(
            logoutUseCase = LogoutUseCase(authRepository),
            getProfileUseCase = GetProfileUseCase(profileRepository)
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initially should load profile data`() = runTest(testDispatcher.scheduler) {
        // Given
        val expectedName = "Alice"
        val expectedEmail = "alice@example.com"
        profileRepository.profile = UserProfile(name = expectedName, email = expectedEmail)

        // When
        viewModel = ProfileViewModel(
            logoutUseCase = LogoutUseCase(authRepository),
            getProfileUseCase = GetProfileUseCase(profileRepository)
        )
        advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.name).isEqualTo(expectedName)
        assertThat(viewModel.state.value.email).isEqualTo(expectedEmail)
    }

    @Test
    fun `OnLogoutClicked success should update state`() = runTest(testDispatcher.scheduler) {
        // Given
        authRepository.logoutResult = Result.Success(Unit)
        authRepository.logoutDelay = 1000

        // When
        viewModel.onIntent(ProfileIntent.OnLogoutClicked)
        runCurrent()

        // Then - Check Loading
        assertThat(viewModel.state.value.isLoading).isTrue()

        advanceUntilIdle()

        // Then - Check Success
        assertThat(viewModel.state.value.isLoading).isEqualTo(false)
        assertThat(viewModel.state.value.isLogoutSuccess).isEqualTo(true)
    }

    @Test
    fun `OnLogoutClicked failure should update state`() = runTest(testDispatcher.scheduler) {
        // Given
        authRepository.logoutResult =
            Result.Error(com.flatrocktech.famousquotequiz.core.domain.error.DataError.NetworkError.UNKNOWN)

        // When
        viewModel.onIntent(ProfileIntent.OnLogoutClicked)
        advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.isLoading).isEqualTo(false)
        assertThat(viewModel.state.value.isLogoutSuccess).isEqualTo(false)
    }
}
