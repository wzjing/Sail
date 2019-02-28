package com.infinitytech.sail.data.source.local

import androidx.room.*
import androidx.room.TypeConverters
import com.infinitytech.sail.data.CollectionBean
import com.infinitytech.sail.data.ProjectBean
import com.infinitytech.sail.util.room.DateConverter
import com.infinitytech.sail.util.room.StringArrayConverter
import com.infinitytech.sail.util.room.StringMapConverter

/**
 * Created by wzjing on 2017/11/8. Application database
 */
@Suppress("RedundantVisibilityModifier", "unused")
@Database(entities = [(ProjectBean::class), (CollectionBean::class)], version = 1)
@TypeConverters(StringMapConverter::class, DateConverter::class, StringArrayConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        public const val databaseName = "AppDatabase.db"
    }

    public abstract fun collectionDao(): CollectionDao
    public abstract fun projectDao(): ProjectDao

    public abstract fun getPersonDao(): PersonDao
}