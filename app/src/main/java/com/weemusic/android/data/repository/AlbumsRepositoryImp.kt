package com.weemusic.android.data.repository

import android.accounts.NetworkErrorException
import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.model.Album
import com.weemusic.android.model.toModel
import com.weemusic.android.shared.util.NetworkConnection
import com.weemusic.android.shared.util.ResponseHandler
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val iTunesApi: ItunesApi,
    private val responseHandler: ResponseHandler,
    private val dispatcher: CoroutineDispatcher
) : AlbumsRepository {

    override suspend fun returnTopAlbums(): Result<List<Album>> {
        return withContext(dispatcher){
            responseHandler.process(iTunesApi.getTopAlbums(limit = 25))
                .mapCatching { it.feed.entry.toModel() }
        }
    }

}

