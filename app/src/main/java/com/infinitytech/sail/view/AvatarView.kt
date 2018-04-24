package com.infinitytech.sail.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import com.infinitytech.sail.R
import com.infinitytech.sail.util.extentions.*

/**
 * Created by wzjing on 2018/4/9 at Sail.
 */
class AvatarView(context: Context, attrs: AttributeSet? = null) : ImageView(context, attrs) {
    private val clipPath = Path()
    private val paint = Paint().apply {
        color = context.color(R.color.colorAvatarStroke)
        style = Paint.Style.STROKE
        strokeWidth = if (isInEditMode) dp(0.5).toFloat() else 2f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        val centerX = (width + paddingStart - paddingEnd).toFloat() / 2
        val centerY = (height + paddingTop - paddingBottom).toFloat() / 2
        val radius = (min(contentWidth, contentHeight) - paint.strokeWidth).toFloat() / 2

        canvas?.apply {
            save()
            clipCircle(centerX, centerY, radius, Path.Direction.CCW, clipPath)
            super.onDraw(this)
            restore()
            drawCircle(centerX, centerY, radius, paint)
        }
    }
}