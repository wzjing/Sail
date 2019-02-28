package com.infinitytech.sail.di

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

/**
 * Created by wzjing on 2018/3/24 at Sail.
 */
val jackson_module = module {
    factory {
        jacksonObjectMapper()
    }
}