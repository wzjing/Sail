package com.infinitytech.sail.util.extentions

val Number.sq get() = this.toDouble() * this.toDouble()
val Number.sqrt get() = Math.sqrt(this.toDouble())
val Number.cb get() = sq * this.toDouble()
val Number.cbrt get() = Math.cbrt(this.toDouble())

fun <T : Number> min(a: T, b: T) = Math.min(a.toDouble(), b.toDouble())
fun <T : Number> max(a: T, b: T) = Math.max(a.toDouble(), b.toDouble())