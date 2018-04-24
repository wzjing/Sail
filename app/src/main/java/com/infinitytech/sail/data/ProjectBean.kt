@file:Suppress("ArrayInDataClass")

package com.infinitytech.sail.data

import android.arch.persistence.room.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.infinitytech.sail.util.room.OwnerArrayConverter
import com.infinitytech.sail.util.room.StringArrayConverter
import com.infinitytech.sail.util.room.StringMapConverter

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Project(val project: ProjectBean,
                   val httpCode: Int)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Entity(tableName = "projects")
@TypeConverters(StringArrayConverter::class, StringMapConverter::class, OwnerArrayConverter::class)
data class ProjectBean(@PrimaryKey val id: Long,
                       val name: String,
                       @ColumnInfo(name = "published_on") val publishedOn: Long,
                       @ColumnInfo(name = "created_on") val createdOn: Long,
                       @ColumnInfo(name = "modified_on") val modifiedOn: Long,
                       val url: String,
                       val privacy: String,
                       val fields: Array<String>,
                       val covers: Map<String, String>,
                       @ColumnInfo(name = "mature_content") val matureContent: Int,
                       @ColumnInfo(name = "mature_access") val matureAccess: String,
                       val owners: Array<Owner>,
//                       val stats: Stats,
                       @ColumnInfo(name = "conceived_on") val conceivedOn: Long,
                       @ColumnInfo(name = "canvas_width") val canvasWidth: Int,
                       val tags: Array<String>,
                       val description: String,
                       @ColumnInfo(name = "editor_version") val editorVersion: Int,
                       @ColumnInfo(name = "allow_comments") val allowComments: Int,
//                       val modules: Array<Module>,
                       @ColumnInfo(name = "short_url") val shortUrl: String,
//                       val copyright: CopyRight,
//                       val tools: Array<Tool>,
//                       val features: Array<Feature>,
                       @ColumnInfo(name = "creator_id") val creatorId: Long)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Projects(val projects: List<ListProjectBean>, val httpCode: Int)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class ListProjectBean(@PrimaryKey val id: Long,
                           val name: String,
                           @ColumnInfo(name = "published_on") val publishedOn: Long,
                           @ColumnInfo(name = "created_on") val createdOn: Long,
                           @ColumnInfo(name = "modified_on") val modifiedOn: Long,
                           val url: String,
                           val privacy: String,
                           @Embedded val fields: List<String>,
                           @Embedded val covers: Map<String, String>,
                           @ColumnInfo(name = "mature_content") val matureContent: Int,
                           @ColumnInfo(name = "mature_access") val matureAccess: String,
                           val owners: List<Owner>,
                           val stats: Stats,
                           @ColumnInfo(name = "conceived_on") val conceivedOn: Long,
                           val features: List<Feature>,
                           val colors: List<Color>)