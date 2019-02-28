package com.infinitytech.sail.data;

import androidx.room.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.infinitytech.sail.util.room.StringArrayConverter;

@Entity(tableName = "Person", indices = {@Index("id")})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@TypeConverters(StringArrayConverter.class)
public class Person {
    @JsonProperty("id") @PrimaryKey @ColumnInfo(name = "id") public int id;
    @ColumnInfo(name = "age") public int age;
    @ColumnInfo(name = "name") public String name;
    @ColumnInfo(name = "gender")public String gender;
}
