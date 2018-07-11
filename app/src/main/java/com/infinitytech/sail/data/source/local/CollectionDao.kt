package com.infinitytech.sail.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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