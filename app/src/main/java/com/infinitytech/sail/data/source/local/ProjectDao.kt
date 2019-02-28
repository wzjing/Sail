package com.infinitytech.sail.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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