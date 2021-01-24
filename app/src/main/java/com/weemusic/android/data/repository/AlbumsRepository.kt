package com.weemusic.android.data.repository

import com.weemusic.android.model.Album
import io.reactivex.Single

interface AlbumsRepository {

    fun preform(): Result<Single<List<Album>>>
}