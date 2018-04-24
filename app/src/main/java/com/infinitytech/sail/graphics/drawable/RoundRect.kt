package com.infinitytech.sail.graphics.drawable

import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Paint
import kotlin.math.min

/**
 * Created by wzjing on 2018/4/15 at Sail.
 */
class RoundRect {

    var left: Int = 0
    var top: Int = 0
    var right: Int = 0
    var bottom: Int = 0

    var rx: Float = 0f
    var ry: Float = 0f
    var radius: Float
        set(value) {
            rx = value
            ry = value
        }
        get() = min(rx, ry)

    val width: Int get() = right - left
    val height: Int get() = bottom - top

    constructor()

    constructor(l: Int, t: Int, r: Int, b: Int, rx: Float, ry: Float) {
        set(l, t, r, b, rx, ry)
    }

    constructor(l: Int, t: Int, r: Int, b: Int, radius: Float) {
        set(l, t, r, b, radius)
    }

    fun set(l: Int, t: Int, r: Int, b: Int, rx: Float, ry: Float) {
        left = l
        right = if (r < l) 0 else r
        top = t
        bottom = if (b < t) 0 else b

        this.rx = if (rx > width / 2) width / 2f else rx
        this.ry = if (rx > height / 2) height / 2f else ry
    }

    fun set(l: Int, t: Int, r: Int, b: Int, radius: Float) {
        set(l, t, r, b, radius, radius)
    }


}

public fun Canvas.drawRoundRect(rRect: RoundRect, paint: Paint) {
    drawRoundRect(
            rRect.left.toFloat(),
            rRect.top.toFloat(),
            rRect.right.toFloat(),
            rRect.bottom.toFloat(),
            rRect.rx,
            rRect.ry,
            paint
    )
}

public fun Outline.setRoundRect(rRect: RoundRect) {
    setRoundRect(rRect.left, rRect.top, rRect.right, rRect.bottom, rRect.radius)
}
