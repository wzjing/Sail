package com.infinitytech.sail.util

import com.infinitytech.sail.util.extentions.cb
import com.infinitytech.sail.util.extentions.cbrt
import com.infinitytech.sail.util.extentions.sq
import com.infinitytech.sail.util.extentions.sqrt
import org.junit.Test

import org.junit.Assert.*

class MathxKtTest {

    @Test
    fun getSq() {
        assertEquals(9.0, 3.0.sq, 0.0)
    }

    @Test
    fun getSqrt() {
        assertEquals(3.0, 9.0.sqrt, 0.0)
    }

    @Test
    fun getCb() {
        assertEquals(27.0, 3.0.cb, 0.0)
    }

    @Test
    fun getCbrt() {
        assertEquals(3.0, 27.0.cbrt, 0.0)
    }
}