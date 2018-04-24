package com.infinitytech.sail

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun spilt_test() {
        val url = "design://designer.com?code=3a490588a239668d685918500f41075e87194066c49542631221ff147dd0bc1d"
        val code = url.split(":")[1].split("?")[1].split("=")
        print("Key: ${code[0]} value: ${code[1]}")
    }

    @Test
    fun convertUTC() {
        val utc: Long = 1509029157
        val date = Date().apply { time = utc * 1000L }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        print("System: ${System.currentTimeMillis()}\n" +
                "ISO 8601: ${dateFormat.format(date)}")
    }

    @Test
    fun parseKotlin() {
        val json = "{\n" +
                "  \"access_token\" : \"29ed478ab86c07f1c069b1af76088f7431396b7c4a2523d06911345da82224a0\",\n" +
                "  \"token_type\" : \"bearer\",\n" +
                "  \"scope\" : \"public write\",\n" +
                "  \"create_at\" : ${Date().time}\n" +
                "}"
//        val instance = ObjectMapper().registerKotlinModule().readValue<TokenBean>(json)
//        println("Instance: ${instance.accessToken}")
    }
}
