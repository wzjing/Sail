@file:JvmName("SpinnerViewKt")

package com.infinitytech.sail.view

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.StateListAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.widget.ListPopupWindow
import android.widget.TextView
import com.infinitytech.sail.R
import com.infinitytech.sail.graphics.drawable.RoundRect
import com.infinitytech.sail.graphics.drawable.drawRoundRect
import com.infinitytech.sail.graphics.drawable.setRoundRect
import com.infinitytech.sail.util.extentions.*
import org.jetbrains.anko.textColor

/**
 * Created by wzjing on 2018/4/14 at Sail.
 */

private val tag = SpinnerView::class.simpleName
private val d = { msg: String -> Log.d(tag, msg) }

class SpinnerView(context: Context, attrs: AttributeSet? = null) : TextView(context, attrs) {

    private val mPopMenu: PopMenu

    init {
        if (background == null) background = SelectedBackground(context.color(R.color.colorAccent))
        if (stateListAnimator == null)
            stateListAnimator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.item_state_list_animator)
        gravity = Gravity.CENTER
        textColor = Color.WHITE
        paddingHorizontal = dp(12)
        paddingVertical = dp(4)

        mPopMenu = PopMenu(context)
    }

    override fun performClick(): Boolean {
        mPopMenu.show()
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (event?.action == MotionEvent.ACTION_BUTTON_PRESS) {
            performClick()
            super.onTouchEvent(event)
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        d("onLayout($left, $top, $right, $bottom)")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        d("onMeasure($left, $top, $right, $bottom)")
    }

    private class PopMenu(context: Context, attrs: AttributeSet? = null)
        : ListPopupWindow(context, attrs) {

        init {
            width = context.dp(100)
            height = context.dp(200)
            setBackgroundDrawable(context.drawable(R.drawable.bg_round_rect))
        }

        override fun show() {
            super.show()
            d("Pop Menu show")
        }

        override fun dismiss() {
            super.dismiss()
            d("Pop Menu dismiss")
        }

    }

    private class SelectedBackground(@ColorInt color: Int) : Drawable() {

        private val paint = Paint().apply {
            style = Paint.Style.FILL
            this.color = color
            isAntiAlias = true
        }

        private var outBounds = RoundRect()

        override fun draw(canvas: Canvas?) {
            canvas?.drawRoundRect(outBounds, paint)
        }

        override fun setAlpha(alpha: Int) {
            paint.alpha = alpha
        }

        override fun getOpacity(): Int = PixelFormat.UNKNOWN

        override fun setColorFilter(colorFilter: ColorFilter?) {
            paint.colorFilter = colorFilter
        }

        override fun setBounds(bounds: Rect?) {
            setOutBounds(bounds?.width() ?: 0, bounds?.height() ?: 0)
        }

        override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
            super.setBounds(left, top, right, bottom)
            setOutBounds(right - left, bottom - top)
        }

        private fun setOutBounds(width: Int, height: Int) {
            outBounds.set(0, 0, width, height, height / 2f)
        }

        override fun getOutline(outline: Outline?) {
            outline?.setRoundRect(outBounds)
        }

    }
}