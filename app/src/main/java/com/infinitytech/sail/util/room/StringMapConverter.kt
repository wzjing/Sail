package com.infinitytech.sail.util.room

import androidx.room.TypeConverter
import android.util.Log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class StringMapConverter {
    companion object {
        private val tag = "MapArrayConverter"
        @TypeConverter
        @JvmStatic
        public fun fromStringMap(map: Map<String, String>) = try {
            val mapper = ObjectMapper()
            mapper.writeValueAsString(map)
        } catch (e: JsonProcessingException) {
            Log.e(tag, "MapArrayConverter Exception: ${e.message}")
            e.printStackTrace()
            ""
        }!!

        @TypeConverter
        @JvmStatic
        public fun toStringMap(json: String) = try {
            ObjectMapper().readValue<Map<String, String>>(json)
        } catch (e: Exception) {
            mapOf<String, String>()
        }
    }

}