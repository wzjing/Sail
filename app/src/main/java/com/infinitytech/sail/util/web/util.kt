package com.infinitytech.sail.util.web

@Suppress("unused", "RedundantVisibilityModifier")
/**
 * error on Application
 */

enum class HttpCode(val value: Int, val description: String) {
    OK(200, "Ok"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    CONNECTION_FAILED(600, "Connection Failed"),
    UNKNOWN_ERROR(700, "Unknown Error");

    companion object {
        fun fromValue(code: Int) = when (code) {
            OK.value -> OK
            FORBIDDEN.value -> FORBIDDEN
            TOO_MANY_REQUESTS.value -> FORBIDDEN
            INTERNAL_SERVER_ERROR.value -> INTERNAL_SERVER_ERROR
            SERVICE_UNAVAILABLE.value -> SERVICE_UNAVAILABLE
            CONNECTION_FAILED.value -> CONNECTION_FAILED
            else -> UNKNOWN_ERROR
        }
    }
}