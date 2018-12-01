package com.infinitytech.sail.di

import com.infinitytech.sail.util.rx.AndroidSchedulerProvider
import com.infinitytech.sail.util.rx.SchedulerProvider
import org.koin.dsl.module.module

/**
 * Created by wzjing on 2018/1/9 at Designer.
 */
val thread_module = module {
    single { AndroidSchedulerProvider() } bind SchedulerProvider::class
}