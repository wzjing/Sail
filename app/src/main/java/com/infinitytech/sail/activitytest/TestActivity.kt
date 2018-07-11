package com.infinitytech.sail.activitytest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.infinitytech.sail.R
import com.infinitytech.sail.util.extentions.dp
import com.infinitytech.sail.view.AvatarView
import org.jetbrains.anko.imageResource

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.GRAY))

        setContentView(object : View(this) {
            val path = Path().apply {
                lineTo(840f, 540f)
                addCircle(540f, 540f, 300f, Path.Direction.CW)
                close()
            }
            val paint = Paint().apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = dp(2).toFloat()
            }

            private val clipPath = Path()

            override fun onDraw(canvas: Canvas?) {
                super.onDraw(canvas)
                canvas ?: return
                if (clipPath.isEmpty) {
                    val radius = { a: Int, b: Int ->
                        (if (a > b) b else a) / 2f
                    }(height - paddingTop - paddingBottom, width - paddingStart - paddingEnd)
                    clipPath.addCircle(
                            (canvas.width - paddingEnd + paddingStart) / 2f,
                            (canvas.height - paddingBottom + paddingTop) / 2f,
                            radius,
                            Path.Direction.CCW)
                }
                canvas.clipPath(clipPath)
                canvas.drawColor(Color.RED)
            }
        })
        setContentView(AvatarView(this))
    }
}