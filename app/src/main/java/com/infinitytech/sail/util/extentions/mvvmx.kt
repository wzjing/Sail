package com.infinitytech.sail.util.extentions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, init: (T?) -> Unit) = observe(owner, Observer<T> { init(it) })