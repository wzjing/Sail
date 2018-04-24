package com.infinitytech.sail.util.jackson

import android.util.Log
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.google.gson.internal.bind.util.ISO8601Utils
import java.io.IOException
import java.text.ParseException
import java.text.ParsePosition
import java.util.*

/**
 * Deserialize ISO 8601 time to Date.
 */
class ISO8601Deserializer : JsonDeserializer<Date>() {

    private val tag = this::class.simpleName

    override fun deserialize(parser: JsonParser?, deserializationContext: DeserializationContext?): Date {
        try {
            val time = parser?.text
            return ISO8601Utils.parse(time, ParsePosition(0))
        } catch (e: ParseException) {
            Log.e(tag, "ISO 8601 ParseException: on $\"${parser?.text}\"")
            e.printStackTrace()
        } catch (e: IOException) {
            Log.e(tag, "ISO 8601 IOException: parser.getText()")
            e.printStackTrace()
        }
        return Date(0)
    }
}