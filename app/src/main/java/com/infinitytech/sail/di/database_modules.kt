package com.infinitytech.sail.di

import android.arch.persistence.room.Room
import com.infinitytech.sail.data.source.local.AppDatabase
import org.koin.dsl.module.applicationContext

/**
 * Created by wzjing on 2018/1/8 at Designer.
 */

object RoomType {
    val STORAGE = "Storage"
    val IN_MEMORY = "InMemory"
}

val room_module = applicationContext {
    bean(RoomType.STORAGE) { Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.databaseName).build() }
    bean(RoomType.IN_MEMORY) { Room.inMemoryDatabaseBuilder(get(), AppDatabase::class.java).build() }
}