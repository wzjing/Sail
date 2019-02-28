package com.infinitytech.sail.data.source.local;

import com.infinitytech.sail.data.Person;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {
    @Insert
    void addItem(Person  person);

    @Query("SELECT * FROM collections WHERE id = :id LIMIT 1")
    LiveData<Person> getItem(long id);
}
