@file:Suppress("ArrayInDataClass")

package com.infinitytech.sail.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

/**
 * Owner
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Owner(val id: Int,
                 val firstName: String?,
                 val lastName: String?,
                 val username: String,
                 val city: String?,
                 val state: String?,
                 val country: String?,
                 val company: String?,
                 val occupation: String?,
                 val createdOn: String?,
                 val url: String?,
                 val displayName: String?,
                 val images: Map<String, String>,
                 val fields: Array<String>,
                 val has_default_image: Boolean,
                 val website: String?,
                 val stats: Stats)

/**
 * Stats
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Stats(val followers: Int,
                 val following: Int,
                 val views: Int,
                 val appreciations: Int,
                 val comments: Int)

/**
 * Module
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Module(val id: Long,
                  val projectId: Long,
                  val type: String,
                  val fullBleed: Int,
                  val alignment: String?,
                  val captionAlignment: String?,
                  val src: String?,
                  val width: Int,
                  val height: Int,
                  val sizes: Map<String, String>?,
                  val dimensionis: Map<String, Dimension>?,
                  val text: String?,
                  val textPlain: String?)

data class Dimension(val width: Int, val height: Int)

/**
 * Copyright
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class CopyRight(
        val license: String,
        val description: String?,
        val licenseId: Int)

/**
 * Tool
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Tool(val id: Long,
                val title: String?,
                val category: String?,
                val categoryLable: String?,
//                val synonym: String?,
                val approved: String?,
                val url: String?)

/**
 * Feature
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Feature(val featuredOn: Long,
                   val site: Site?)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Site(val id: Long,
                val parentId: Long,
                val name: String?,
                val key: String?,
                val icon: String?,
                val appIcon: String?,
                val domain: String?,
                val url: String?,
                val ribbon: Map<String, String>)

typealias AndroidColor = android.graphics.Color

data class Color(val h: Float,
                 val s: Float,
                 val v: Float,
                 val r: Int,
                 val g: Int,
                 val b: Int) {
    val rgb: Int
        get() = AndroidColor.rgb(r, g, b)
}