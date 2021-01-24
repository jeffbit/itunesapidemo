package com.weemusic.android.data.repository

import android.accounts.NetworkErrorException
import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.model.Album
import com.weemusic.android.model.toModel
import com.weemusic.android.shared.util.NetworkConnection
import io.reactivex.Single
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val iTunesApi: ItunesApi,
    private val networkConnection: NetworkConnection
) : AlbumsRepository {

    override fun preform(): Result<Single<List<Album>>> {

        return when (networkConnection.isNetworkConnected()) {
            true -> {
                Result.success(iTunesApi.getTopAlbums(limit = 25)
                    .map { it.feed.entry.toModel() })
            }
            false -> {
                Result.failure(Exception())
            }
        }
    }

}

