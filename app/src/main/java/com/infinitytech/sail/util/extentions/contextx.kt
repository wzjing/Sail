package com.infinitytech.sail.util.extentions

import android.content.Context
import android.os.Build
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.widget.Toast

fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun Context.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
fun Context.integer(@IntegerRes id: Int) = resources.getInteger(id)
fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, length).show()
