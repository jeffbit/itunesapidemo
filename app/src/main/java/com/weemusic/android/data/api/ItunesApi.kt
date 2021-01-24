package com.weemusic.android.data.api

import com.weemusic.android.data.model.FeedEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ItunesApi {

    @GET("/us/rss/topalbums/limit={limit}/json")
    fun getTopAlbums(@Path("limit") limit: Int = 25): Single<FeedEntity>
}