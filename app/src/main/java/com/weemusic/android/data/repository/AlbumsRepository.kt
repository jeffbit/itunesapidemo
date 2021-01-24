package com.weemusic.android.data.repository

import com.weemusic.android.data.model.FeedEntity
import com.weemusic.android.domain.Album
import io.reactivex.Observable
import io.reactivex.Single

interface AlbumsRepository {

    fun preform(): Result<Observable<List<Album>>>
}