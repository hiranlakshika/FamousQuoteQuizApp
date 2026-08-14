package com.flatrocktech.famousquotequiz.core.data

import com.flatrocktech.famousquotequiz.core.domain.SessionStorage
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import com.flatrocktech.famousquotequiz.feature.auth.data.remote.dto.LoginResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {

    fun create(
        engine: HttpClientEngine,
        sessionStorage: SessionStorage,
        appLogger: AppLogger
    ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        appLogger.debug("HTTP") { message }
                    }
                }
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = sessionStorage.getToken()
                        val refreshToken = sessionStorage.getRefreshToken()
                        if (token != null && refreshToken != null) {
                            BearerTokens(token, refreshToken)
                        } else {
                            null
                        }
                    }
                    refreshTokens {
                        val refreshToken =
                            sessionStorage.getRefreshToken() ?: return@refreshTokens null

                        val response = client.post("auth/refresh") {
                            setBody(mapOf("refreshToken" to refreshToken))
                            sessionStorage.getToken()?.let {
                                bearerAuth(it)
                            }
                        }

                        if (response.status == HttpStatusCode.OK) {
                            val dto = response.body<LoginResponseDto>()
                            sessionStorage.saveToken(dto.token)
                            sessionStorage.saveRefreshToken(dto.refreshToken)
                            BearerTokens(dto.token, dto.refreshToken)
                        } else {
                            sessionStorage.clearSession()
                            null
                        }
                    }
                }
            }
            defaultRequest {
                url(NetworkingConstants.BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }
    }
}