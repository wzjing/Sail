package com.infinitytech.sail.data.source

import android.content.Context
import com.infinitytech.sail.data.CollectionBean
import com.infinitytech.sail.data.source.local.AppDatabase
import com.infinitytech.sail.di.RoomType
import com.infinitytech.sail.util.web.WebRequest
import com.infinitytech.sail.web.WebClient
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Repository for requesting shots list.
 */
class CollectionRepository : KoinComponent {

    private val tag = this::class.simpleName

    private val webServer: WebClient by inject()
    private val appDb: AppDatabase by inject(RoomType.STORAGE)
    private val context: Context by inject()

    fun getCollections(page: Int = 1, perPage: Int = 18, callback: WebRequest<List<CollectionBean>>.() -> Unit) {
        val request = WebRequest<List<CollectionBean>>()
        request.callback()
//        webServer.getCollections(
//                timeframe = WebClient.SHOTS_TIMEFRAME_MONTH,
//                date = TimeConverter.format(Date().apply { time -= 1000 * 3600 * 24 }),
//                sort = WebClient.SHOTS_SORT_RECENT,
//                page = page,
//                perPage = perPage)
//                .enqueue(object : Callback<List<ShotBean>> {
//                    override fun onResponse(call: Call<List<ShotBean>>?, response: Response<List<ShotBean>>?) {
//                        Log.d(tag, "Request: ${call?.request()?.url()}")
//                        response!!
//                        if (response.isSuccessful) {
//                            Log.d(tag, "Response Array Size: ${response.body()?.size}")
//                            request.success.invoke(response.body() ?: emptyList())
//                        } else {
//                            Log.e(tag, "Error: ${response.code()} : ${response.errorBody().toString()}" +
//                                    "\nBody: ${response.body()}")
//                            request.failed.invoke(response.code(), "Error-${response.body().toString()}")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<List<ShotBean>>?, t: Throwable?) {
//                        request.failed.invoke(error.connectionFailed, t?.message.toString())
//                        Log.d(tag, "Failed: ${t?.message}")
//                        t?.printStackTrace()
//                    }
//
//                })
    }

    fun getCollection(shotId: Int, callback: WebRequest<CollectionBean>.() -> Unit) {
        val request = WebRequest<CollectionBean>()
        request.callback()
//        webServer.getCollection(shotId).enqueue(object : Callback<ShotBean> {
//            override fun onResponse(call: Call<ShotBean>?, response: Response<ShotBean>?) {
//                response!!
//                if (response.isSuccessful)
//                    request.success.invoke(response.body()!!)
//                else
//                    request.failed.invoke(response.code(), response.errorBody().toString())
//            }
//
//            override fun onFailure(call: Call<ShotBean>?, t: Throwable?) {
//                request.failed.invoke(error.connectionFailed, t?.message.toString())
//                t?.printStackTrace()
//            }
//        })
    }
}