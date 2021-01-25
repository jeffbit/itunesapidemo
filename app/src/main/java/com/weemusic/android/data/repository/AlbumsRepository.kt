package com.weemusic.android.data.repository

import com.weemusic.android.model.Album
import io.reactivex.Single

interface AlbumsRepository {

    suspend fun returnTopAlbums(): Result<List<Album>>
}