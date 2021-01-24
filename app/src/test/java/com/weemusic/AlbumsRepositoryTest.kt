package com.weemusic

import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.model.FeedEntity
import com.weemusic.android.data.repository.AlbumsRepositoryImp
import com.weemusic.android.model.toModel
import com.weemusic.android.shared.util.NetworkConnection
import com.weemusic.android.shared.util.TestUtil
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumsRepositoryTest {


    private lateinit var mockItunesApi: ItunesApi
    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var mockFeedEntitySingle: Single<FeedEntity>
    private lateinit var albumsRepositoryImpl: AlbumsRepositoryImp


    @Before
    fun setup() {
        mockItunesApi = mockk()
        mockNetworkConnection = mockk()
        mockFeedEntitySingle = mockk()

        albumsRepositoryImpl = AlbumsRepositoryImp(
            iTunesApi = mockItunesApi,
            networkConnection = mockNetworkConnection
        )
    }

    @Test
    fun `should return false when network connect is disabled`() {
        //given
        every { mockNetworkConnection.isNetworkConnected() } answers { false }

        //when
        val result = albumsRepositoryImpl.preform()

        //then
        Assert.assertEquals(result.isFailure, true)
    }

    @Test
    fun `should return true when album is retrieved`() {
        //given
        val feedEntity = TestUtil.feedEntity
        every { mockNetworkConnection.isNetworkConnected() } answers { true }
        every { mockItunesApi.getTopAlbums() } answers { mockFeedEntitySingle }
        every { mockFeedEntitySingle.blockingGet() } answers { feedEntity }

        //when
        val result = albumsRepositoryImpl.preform().getOrThrow().blockingGet()
        //then


        Assert.assertEquals(result, feedEntity.feed.entry.toModel())
    }

}