package com.infinitytech.sail.util.extentions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, init: (T?) -> Unit) = observe(owner, Observer<T> { init(it) })