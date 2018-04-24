package com.infinitytech.sail.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Tools for time format
 */
object TimeConverter {

    public val formatDefault = "yyyy-MM-dd"

    public fun format(date: Date) = SimpleDateFormat(formatDefault, Locale.getDefault()).format(date)!!
}