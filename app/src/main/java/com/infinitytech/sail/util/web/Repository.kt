package com.infinitytech.sail.util.web

abstract class Repository<T> {
    abstract fun request(request: WebRequest<T>)
}