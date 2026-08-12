package com.flatrocktech.famousquotequiz.core.domain.error

sealed interface DataError : Error {

    enum class UnknownError : DataError {
        SOMETHING_WENT_WRONG,
    }

    enum class NetworkError : DataError {
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        REQUEST_FAILED,
        UNAUTHORIZED,
        UNKNOWN
    }
}