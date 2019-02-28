package com.infinitytech.sail.web

import androidx.annotation.IntRange
import androidx.annotation.StringDef
import com.infinitytech.sail.data.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

private const val VERSION = "v2"

@Suppress("RedundantVisibilityModifier", "unused")
interface WebClient {
    /**
     * @sample Post https://server.com/register/ username:wzjing
     * <code>
     *     @FormUrlEncoded
     *     @POST("/register/")
     *     public fun register(@Field("username") username: String)
     * </code>
     *
     * @sample Get https://server.com/users?gender="female"
     * <code>
     *     @GET("/users")
     *     @Headers("Header Name: Content")
     *     public fun getFemale(@Query("gender") gender: String): Call<List<User>>
     * </code>
     *
     * @sample Url Path https://server.com/users/103
     * <code>
     *     @GET("/users/{id}")
     *     public fun getUser(@Path("id") id: Int): Call<User>
     * </code>
     */

    @GET("/$VERSION/projects")
    public fun getProjects(
            @Query("q") query: String?,
            @Query("sort") sort: String?,
            @Query("time") time: String?,
            @Query("field") field: String?,
            @Query("country") country: String?,
            @Query("state") state: String?,
            @Query("city") city: String?,
            @Query("page") page: Int,
            @Query("tags") tags: String?,
            @Query("color_hex") colorHex: String?,
            @Query("color_range") colorRange: String?,
            @Query("license") license: String?): Call<Projects>

    @GET("/$VERSION/projects/{project_id}")
    public fun getProject(@Path("project_id") id: Long): Call<Project>

    @GET("/$VERSION/projects/{project_id}/comments")
    public fun getProjectComment(@Path("project_id") id: Long)

    @GET("/$VERSION/collections")
    public fun getCollections(
            @Query("q") query: String = "",
            @Query("time") @CollectionTimeDef time: String
            = CollectionTime.ALL.value,
            @Query("page") @IntRange(from = 1) page: Int = 1,
            @Query("sort") @CollectionSortDef sort: String
            = CollectionSort.VIEWS.value)

    @GET("/$VERSION/collections/{collection_id}")
    public fun getCollection(@Path("collection_id") id: Int)

    @GET("/$VERSION/collections/{collection_id}/projects")
    public fun getCollectionProjects(
            @Path("collection_id") id: Int,
            @Query("time") @CollectionTimeDef time: String
            = CollectionTime.ALL.value,
            @Query("page") @IntRange(from = 1) page: Int = 1,
            @Query("sort") @CollectionSortDef sort: String
            = CollectionSort.VIEWS.value,
            @Query("per_page") @IntRange(from = 1, to = 20) perPage: Int = 12)

    @GET("/$VERSION/fields")
    public fun getFields(): Call<FieldBean>

}

/** Arguments **/

@StringDef("all", "today", "week", "month")
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class CollectionTimeDef

enum class CollectionTime(val value: String) {
    ALL("all"),
    TODAY("today"),
    WEEK("week"),
    MONTH("month")
}

@StringDef("comments", "views", "last_item_added_date")
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.BINARY)
annotation class CollectionSortDef

enum class CollectionSort(val value: String) {
    COMMENTS("comments"),
    VIEWS("views"),
    LAST_ITEM_ADDED_DATE("last_item_added_date")
}