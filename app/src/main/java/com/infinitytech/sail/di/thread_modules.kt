package com.infinitytech.sail.di

import com.infinitytech.sail.util.rx.AndroidSchedulerProvider
import com.infinitytech.sail.util.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.applicationContext

/**
 * Created by wzjing on 2018/1/9 at Designer.
 */
val thread_module = applicationContext {
    bean { AndroidSchedulerProvider() } bind SchedulerProvider::class
}