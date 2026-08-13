package com.flatrocktech.famousquotequiz.core.data.util

import com.flatrocktech.famousquotequiz.core.domain.Result
import com.flatrocktech.famousquotequiz.core.domain.error.DataError
import com.flatrocktech.famousquotequiz.core.domain.util.AppLogger
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive


suspend inline fun <reified T> safeCall(
    appLogger: AppLogger? = null,
    execute: () -> HttpResponse
): Result<T, DataError.NetworkError> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        appLogger?.error("HTTP", e) { "Socket timeout" }
        return Result.Error(DataError.NetworkError.REQUEST_FAILED)
    } catch (e: UnresolvedAddressException) {
        appLogger?.error("HTTP", e) { "No internet connection" }
        return Result.Error(DataError.NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        appLogger?.error("HTTP", e) { "Unknown network error" }
        return Result.Error(DataError.NetworkError.UNKNOWN)
    }

    return responseToResult(response, appLogger)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse,
    appLogger: AppLogger? = null
): Result<T, DataError.NetworkError> = when (response.status.value) {
    in 200..299 -> {
        try {
            Result.Success(response.body<T>())
        } catch (e: NoTransformationFoundException) {
            appLogger?.error("HTTP", e) { "Serialization error" }
            Result.Error(DataError.NetworkError.SERIALIZATION)
        }
    }

    401 -> Result.Error(DataError.NetworkError.UNAUTHORIZED)
    408 -> Result.Error(DataError.NetworkError.REQUEST_FAILED)
    429 -> Result.Error(DataError.NetworkError.REQUEST_FAILED)
    in 500..599 -> Result.Error(DataError.NetworkError.SERVER_ERROR)
    else -> Result.Error(DataError.NetworkError.UNKNOWN)
}