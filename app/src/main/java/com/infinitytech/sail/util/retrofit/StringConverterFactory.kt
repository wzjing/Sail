package com.infinitytech.sail.util.retrofit

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Retrofit String converter factory.
 */
public class StringConverterFactory : Converter.Factory() {
    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        if (String::class == type) {
            return Converter<String, RequestBody>{value -> RequestBody.create(MediaType.parse("text/plain"), value) }
        }
        return null
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        if (String::class == type) {
            return Converter<ResponseBody, String> { value -> value.toString() }
        }
        return null
    }
}