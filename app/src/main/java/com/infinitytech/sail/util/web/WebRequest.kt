package com.infinitytech.sail.util.web

open class WebRequest<T> {
    var success: (T) -> Unit = {}
    var failed: (HttpCode) -> Unit = { _ -> }

    fun success(onSuccess: (T) -> Unit) {
        success = onSuccess
    }

    fun failed(onFailed: (error: HttpCode) -> Unit) {
        failed = onFailed
    }
}