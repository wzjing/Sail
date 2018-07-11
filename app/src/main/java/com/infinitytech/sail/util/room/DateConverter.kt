package com.infinitytech.sail.util.room

import androidx.room.TypeConverter
import java.util.*

/**
 * Date converter for room
 */
public class DateConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        public fun fromDate(date: Date) = date.time

        @TypeConverter
        @JvmStatic
        public fun toDate(timestamp: Long) = Date(timestamp)
    }
}