package com.infinitytech.sail.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.infinitytech.sail.data.ProjectBean

@Dao
interface ProjectDao {
    @Insert
    fun addItem(project: ProjectBean)

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    fun getProject(id: Long): LiveData<ProjectBean>

    @Query("SELECT * FROM projects")
    fun getAll(): LiveData<List<ProjectBean>>

    @Delete
    fun delete(vararg project: ProjectBean)
}