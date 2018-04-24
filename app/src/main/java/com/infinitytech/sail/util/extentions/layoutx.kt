@file:Suppress("unused", "ClassName")
@file:JvmName("LayoutX")

package com.infinitytech.sail.util.extentions

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import java.util.*

const val match_parent = ViewGroup.LayoutParams.MATCH_PARENT
const val wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT

/** Basic function **/

// Params
fun vlParams(width: Int, height: Int): ViewGroup.LayoutParams = ViewGroup.LayoutParams(width, height)

inline fun <T : View> T.lparams(width: Int, height: Int, init: ViewGroup.LayoutParams.() -> Unit = {}): T {
    val params = this.layoutParams ?: vlParams(width, height)
    params.init()
    this.layoutParams = params
    return this
}

// Size
fun Context.dp(value: Number) = (value.toFloat() * resources.displayMetrics.density).toInt()

fun Context.sp(value: Number) = (value.toFloat() * resources.displayMetrics.density).toInt()

fun View.dp(value: Number) = context.dp(value)
fun View.sp(value: Number) = context.sp(value)

val Context.screenWidth get() = resources.displayMetrics.widthPixels
val Context.screenHeight get() = resources.displayMetrics.heightPixels
val Context.screenWidthDpi get() = resources.displayMetrics.xdpi
val Context.screenHeightDip get() = resources.displayMetrics.ydpi
val Context.navigationBarHeight get() = resources.getDimensionPixelSize(resources
        .getIdentifier("navigation_bar_height", "dimen", "android"))

var ViewGroup.MarginLayoutParams.margin: Int
    set(value) {
        setMargins(value, value, value, value)
    }
    get() {
        return arrayOf(marginStart, marginEnd, topMargin, bottomMargin).max() ?: 0
    }

// Event

fun View.onClick(init: (v: View) -> Unit) = setOnClickListener(init)
fun View.onLongClick(init: (v: View) -> Boolean) = setOnLongClickListener(init)
fun View.onTouch(init: (v: View, event: MotionEvent) -> Boolean) = setOnTouchListener(init)
fun MotionEvent.down(init: () -> Any) = if (action == MotionEvent.ACTION_DOWN) init() else Unit
fun MotionEvent.up(init: () -> Any) = if (action == MotionEvent.ACTION_UP) init() else Unit
fun MotionEvent.move(init: () -> Any) = if (action == MotionEvent.ACTION_MOVE) init() else Unit
fun MotionEvent.pointerDown(init: () -> Any) = if (action == MotionEvent.ACTION_POINTER_DOWN) init() else Unit
fun MotionEvent.pointerUp(init: () -> Any) = if (action == MotionEvent.ACTION_POINTER_UP) init() else Unit
fun MotionEvent.press(init: () -> Any) = if (action == MotionEvent.ACTION_BUTTON_PRESS) init() else Unit
fun MotionEvent.cancel(init: () -> Any) = if (action == MotionEvent.ACTION_CANCEL) init() else Unit

/** Layout Retrofit **/

class _ConstraintLayout(context: Context) : ConstraintLayout(context) {
    @Suppress("PropertyName")
    val PARENT
        get() = ConstraintLayout.LayoutParams.PARENT_ID
    @Suppress("PropertyName")
    val match_constraint = 0

    inline fun <T : View> T.lparams(
            width: Int,
            height: Int,
            init: ConstraintLayout.LayoutParams.() -> Unit = {}
    ): T = this.apply {
        layoutParams = ConstraintLayout.LayoutParams(width, height).apply(init)
        this@_ConstraintLayout.addView(this)
    }
}

class _FrameLayout(context: Context) : FrameLayout(context) {
    inline fun <T : View> T.lparams(
            width: Int,
            height: Int,
            init: FrameLayout.LayoutParams.() -> Unit = {}
    ): T = this.apply {
        layoutParams = FrameLayout.LayoutParams(width, height).apply(init)
        this@_FrameLayout.addView(this)
    }
}

/** ConstraintLayout **/
inline fun Context.constraintLayout(init: _ConstraintLayout.() -> Unit = {}) = _ConstraintLayout(this).apply(init)

inline fun ViewGroup.constraintLayout(init: _ConstraintLayout.() -> Unit = {}) = this.context.constraintLayout(init)

/** FrameLayout **/
inline fun Context.frameLayout(init: _FrameLayout.() -> Unit) = _FrameLayout(this).apply(init)

inline fun ViewGroup.frameLayout(init: _FrameLayout.() -> Unit) = this.context.frameLayout(init)

object IdManager {
    val ID = lazy { hashMapOf<String, Int>() }
}

fun String.newId(): Int = View.generateViewId().apply {
    IdManager.ID.value[this@newId] = this
}

fun String.id(): Int =
        if (IdManager.ID.value.containsKey(this)) IdManager.ID.value[this]!!
        else throw NoSuchElementException("ID not exist: $this")

inline fun ViewGroup.tv(
        id: Int = View.NO_ID,
        text: String? = null,
        hint: String? = null,
        init: TextView.() -> Unit = {}
) = TextView(context).apply {
    this.id = id
    this.text = text
    this.hint = hint
    init()
}

inline fun ViewGroup.iv(id: Int = View.NO_ID, init: ImageView.() -> Unit = {}) =
        ImageView(context).apply {
            this.id = id
            init()
        }