package com.flatrocktech.famousquotequiz.feature.auth.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.flatrocktech.famousquotequiz.core.domain.FakeSessionStorage
import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.util.FakeAppLogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test

class AuthRepositoryImplTest {

    private lateinit var sessionStorage: FakeSessionStorage
    private lateinit var appLogger: FakeAppLogger
    private lateinit var repository: AuthRepositoryImpl

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @BeforeTest
    fun setup() {
        sessionStorage = FakeSessionStorage()
        appLogger = FakeAppLogger()
    }

    private fun createRepository(mockEngine: MockEngine) {
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url("http://localhost/")
                contentType(ContentType.Application.Json)
            }
        }
        repository = AuthRepositoryImpl(httpClient, sessionStorage, appLogger)
    }

    @Test
    fun `login success should save token and user info and return AuthInfo`() = runTest {
        // Given
        val loginResponseJson = """
            {
                "token": "test-token",
                "expiresAt": "2026-12-31T23:59:59Z",
                "user": {
                    "id": 1,
                    "email": "test@example.com",
                    "displayName": "Test User",
                    "memberSince": "2026-01-01T00:00:00Z"
                }
            }
        """.trimIndent()

        val mockEngine = MockEngine { _ ->
            try {
                respond(
                    content = loginResponseJson,
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            } catch (e: Exception) {
                println("MockEngine Error: ${e.message}")
                throw e
            }
        }
        createRepository(mockEngine)

        // When
        val result = repository.login("test@example.com", "password")

        // Then
        if (result is Result.Error) {
            val errorMessage = "Expected Success but got Error: ${result.error}. " +
                    "Last error logged: ${appLogger.lastError?.message}. " +
                    "Stacktrace: ${appLogger.lastError?.stackTraceToString()}"
            println(errorMessage)
            error(errorMessage)
        }

        val authInfo = (result as Result.Success).data
        assertThat(authInfo.token).isEqualTo("test-token")
        assertThat(authInfo.user.email).isEqualTo("test@example.com")

        assertThat(sessionStorage.savedToken).isEqualTo("test-token")
        assertThat(sessionStorage.savedEmail).isEqualTo("test@example.com")
        assertThat(sessionStorage.savedDisplayName).isEqualTo("Test User")
    }

    @Test
    fun `logout should clear session storage`() = runTest {
        // Given
        sessionStorage.savedToken = "some-token"
        sessionStorage.savedEmail = "test@example.com"
        createRepository(MockEngine { respond("") })

        // When
        repository.logout()

        // Then
        assertThat(sessionStorage.savedToken).isEqualTo(null)
        assertThat(sessionStorage.savedEmail).isEqualTo(null)
    }
}
