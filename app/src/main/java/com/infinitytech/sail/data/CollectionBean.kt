@file:Suppress("ArrayInDataClass")

package com.infinitytech.sail.data

import android.arch.persistence.room.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.fasterxml.jackson.module.kotlin.readValue
import com.infinitytech.sail.util.room.StringArrayConverter
import com.infinitytech.sail.util.room.StringMapConverter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

/**
 * Created by wzjing on 2018/2/10 at Sail.
 */
@Entity(tableName = "collections", indices = [Index("id")])
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@TypeConverters(StringArrayConverter::class)
data class CollectionBean(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                          @ColumnInfo(name = "created_on") val createdOn: Long,
                          @ColumnInfo(name = "admin_lock") val adminLock: Int,
                          @ColumnInfo(name = "title") val title: String,
//                          @ColumnInfo(name = "owners") val owners: Array<Owner>,
                          @ColumnInfo(name = "stats") val stats: String,
                          @ColumnInfo(name = "images") val images: Array<String>,
                          @ColumnInfo(name = "modified_on") val modifiedOn: Long)
