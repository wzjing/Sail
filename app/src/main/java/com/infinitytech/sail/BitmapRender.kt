package com.infinitytech.sail

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.RenderScript
import com.infinitytech.designer.ScriptC_gaussianblur

class BitmapRender {
    companion object {
        public fun bitmapSkech(bitmap: Bitmap, context: Context) : Bitmap {
            val rs = RenderScript.create(context)
            val rScript = ScriptC_gaussianblur(rs)
            val input = Allocation.createFromBitmap(rs, bitmap)
            val output = Allocation.createTyped(rs, input.type)

            rScript.forEach_invert(input, output)

            output.copyTo(bitmap)
            rs.destroy()
            rScript.destroy()
            input.destroy()
            output.destroy()
            return bitmap
        }
    }
}