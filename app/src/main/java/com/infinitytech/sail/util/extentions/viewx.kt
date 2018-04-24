@file:Suppress("unused")

package com.infinitytech.sail.util.extentions

import android.graphics.Canvas
import android.graphics.Path
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast

fun View.toast(message: String, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(context, message, length).show()

// Properties
val View.centerX get() = (left + right) / 2
val View.centerY get() = (top + bottom) / 2
val View.contentWidth get() = width - paddingStart - paddingEnd
val View.contentHeight get() = height - paddingTop - paddingBottom

// Events
fun View.onMyLayoutFinished(init: () -> Unit) {
    val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (isShown) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                init()
            }
        }
    }
    viewTreeObserver.addOnGlobalLayoutListener(listener)
}

// Draw
fun Canvas.clipCircle(x: Float, y: Float, r: Float, direction: Path.Direction, pathHolder: Path) {
    pathHolder.reset()
    pathHolder.addCircle(x, y, r, direction)
    clipPath(pathHolder)
}

var View.padding: Int
    set(value) {
        setPadding(value, value, value, value)
    }
    get() = arrayOf(paddingTop, paddingBottom, paddingStart, paddingEnd).max() ?: 0

var View.paddingVertical: Int
    set(value) {
        setPadding(paddingLeft, value, paddingRight, value)
    }
    get() = arrayOf(paddingTop, paddingBottom).max() ?: 0

var View.paddingHorizontal: Int
    set(value) {
        setPadding(value, paddingTop, value, paddingBottom)
    }
    get() = arrayOf(paddingTop, paddingBottom).max() ?: 0