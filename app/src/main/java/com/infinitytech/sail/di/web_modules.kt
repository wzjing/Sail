package com.infinitytech.sail.di

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.infinitytech.sail.web.WebClient
import com.infinitytech.sail.util.retrofit.StringConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module.applicationContext
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by wzjing on 2018/1/8 at Designer.
 */

val retrofit_module = applicationContext {

    factory {
        Log.d("web_modules", getProperty("client_id"))
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder()
                        .url(chain.request().url().newBuilder()
                                .addQueryParameter("client_id", getProperty("client_id"))
                                .build())
                        .build())
            }
        }.build()
    }

    factory {
        Retrofit.Builder()
                .baseUrl("https://www.behance.net/")
                .client(get())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
                .addConverterFactory(StringConverterFactory())
                .build()
                .create(WebClient::class.java)
    }

}