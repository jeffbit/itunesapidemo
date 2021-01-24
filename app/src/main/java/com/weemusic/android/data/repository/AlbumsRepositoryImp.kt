package com.weemusic.android.data.repository

import android.accounts.NetworkErrorException
import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.domain.Album
import com.weemusic.android.domain.toModel
import com.weemusic.android.util.NetworkConnection
import io.reactivex.Observable
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val ItunesApi: ItunesApi,
    private val networkConnection: NetworkConnection
) : AlbumsRepository {

    override fun preform(): Result<Observable<List<Album>>> {
        return when (networkConnection.isNetworkConnected()) {
            true -> {
                Result.success(ItunesApi.getTopAlbums(limit = 25)
                    .map { it.feed.entry.toModel() }.toObservable())
            }
            false -> {
                Result.failure(NetworkErrorException())
            }

        }


    }
}