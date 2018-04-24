package com.infinitytech.sail.data.source

import android.content.Context
import com.infinitytech.sail.data.ListProjectBean
import com.infinitytech.sail.data.Project
import com.infinitytech.sail.data.ProjectBean
import com.infinitytech.sail.data.Projects
import com.infinitytech.sail.data.source.local.AppDatabase
import com.infinitytech.sail.di.RoomType
import com.infinitytech.sail.util.web.HttpCode
import com.infinitytech.sail.util.web.WebRequest
import com.infinitytech.sail.web.WebClient
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepository : KoinComponent {
    private val tag = this::class.simpleName

    private val webServer: WebClient by inject()
    private val appDb: AppDatabase by inject(RoomType.STORAGE)
    private val context: Context by inject()

    fun getProjects(q: String? = null,
                    sort: String? = null,
                    time: String? = null,
                    field: String? = null,
                    country: String? = null,
                    state: String? = null,
                    city: String? = null,
                    page: Int = 1,
                    tags: String? = null,
                    colorHex: String? = null,
                    colorRange: String? = null,
                    license: String? = null,
                    cb: WebRequest<List<ListProjectBean>>.() -> Any) {
        val webRequest = WebRequest<List<ListProjectBean>>()
        webRequest.cb()
        webServer.getProjects(q, sort, time, field, country, state, city, page, tags, colorHex,
                colorRange, license).enqueue(object : Callback<Projects> {

            override fun onResponse(call: Call<Projects>, response: Response<Projects>) {
                if (response.isSuccessful) {
                    webRequest.success(response.body()?.projects ?: emptyList())
                } else {
                    webRequest.failed(HttpCode.fromValue(response.code()))
                }
            }

            override fun onFailure(call: Call<Projects>, t: Throwable) {
                t.printStackTrace()
                webRequest.failed(HttpCode.CONNECTION_FAILED)
            }

        })

    }

    fun getProject(id: Long, cb: WebRequest<ProjectBean>.() -> Any) {
        val webRequest = WebRequest<ProjectBean>()
        webRequest.cb()
        webServer.getProject(id).enqueue(object : Callback<Project> {

            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                if (response.isSuccessful) {
                    response.body()?.apply {
                        webRequest.success(project)
                    }
                } else {
                    webRequest.failed(HttpCode.fromValue(response.code()))
                }
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                t.printStackTrace()
                webRequest.failed(HttpCode.CONNECTION_FAILED)
            }

        })
    }
}