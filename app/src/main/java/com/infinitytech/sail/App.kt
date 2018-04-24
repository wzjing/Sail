package com.infinitytech.sail

import android.app.Application
import com.infinitytech.sail.di.all_modules
import org.koin.android.ext.android.setProperty
import org.koin.android.ext.android.startKoin


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin(this, all_modules, mapOf("client_id" to getString(R.string.client_id)))
    }

}