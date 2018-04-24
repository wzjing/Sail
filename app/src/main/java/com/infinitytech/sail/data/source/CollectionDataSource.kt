package com.infinitytech.sail.data.source

import com.infinitytech.sail.data.CollectionBean

/**
 * Created by wzjing on 2018/2/7 at Sail.
 */
interface CollectionDataSource {
    fun getCollection(callback: (List<CollectionBean>) -> Any)
}