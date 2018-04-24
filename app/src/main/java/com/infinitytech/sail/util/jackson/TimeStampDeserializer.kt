package com.infinitytech.sail.util.jackson

import android.util.Log
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.util.*

/**
 * Deserialize dribbble date (Tips: dribbble use timestamp/1000 for time point)
 */
class TimeStampDeserializer : JsonDeserializer<Date>() {
    private val tag = this::class.simpleName
    override fun deserialize(parser: JsonParser?, deserializationContext: DeserializationContext?): Date {
        Log.d(tag, "deserialize: ${parser?.text}")
        var timeStamp: Long? = null
        try {
            timeStamp = parser?.longValue ?: throw IllegalArgumentException("parsing timeStamp is null on \"${parser?.text}\"")
        } catch (e: JsonParseException) {
            Log.e(tag, "JsonParseException: unable to parse \"${parser?.text}\"")
            e.printStackTrace()
        } catch (e: IOException) {
            Log.e(tag, "IOException: unable to parse \"${parser?.text}\"")
            e.printStackTrace()
        } finally {
            if (timeStamp == null)
                timeStamp = 0
        }
        return Date(timeStamp * 1000)
    }
}