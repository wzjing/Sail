package com.infinitytech.sail.util.room

import androidx.room.TypeConverter
import android.util.Log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@Suppress("RedundantVisibilityModifier")
/**
 * String converter for room
 */

class StringArrayConverter {
    companion object {
        private val tag = "StringArrayConverter"
        @TypeConverter
        @JvmStatic
        public fun fromStringArray(array: Array<String>) = try {
            ObjectMapper().writeValueAsString(array)
        } catch (e: JsonProcessingException) {
            Log.e(tag, "StringArrayConverter Exception: ${e.message}")
            e.printStackTrace()
            ""
        }!!

        @TypeConverter
        @JvmStatic
        public fun toStringArray(json: String) = try {
            ObjectMapper().readValue<Array<String>>(json)
        } catch (e: Exception) {
            arrayOf<String>()
        }
    }
}