package com.infinitytech.sail.util.room

import android.arch.persistence.room.TypeConverter
import android.util.Log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.infinitytech.sail.data.Owner

/**
 * Created by wzjing on 2018/4/10 at Sail.
 */
class OwnerArrayConverter {
    companion object {
        private val tag = "OwnerArrayConverter"
        @TypeConverter
        @JvmStatic
        public fun fromOwnerArray(array: Array<Owner>) = try {
            ObjectMapper().writeValueAsString(array)
        } catch (e: JsonProcessingException) {
            Log.e(tag, "StringArrayConverter Exception: ${e.message}")
            e.printStackTrace()
            ""
        }!!

        @TypeConverter
        @JvmStatic
        public fun toOwnerArray(json: String) = try {
            ObjectMapper().readValue<Array<Owner>>(json)
        } catch (e: Exception) {
            arrayOf<Owner>()
        }
    }
}