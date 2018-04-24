package com.infinitytech.sail.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.infinitytech.sail.data.CollectionBean

@Dao
interface CollectionDao {
    @Insert
    fun addItem(item: CollectionBean)

    @Query("SELECT * FROM collections WHERE id = :id LIMIT 1")
    fun getItem(id: Long): LiveData<CollectionBean>

    @Query("SELECT * FROM collections")
    fun getAll(): LiveData<List<CollectionBean>>

    @Delete
    fun delete(vararg item: CollectionBean)
}