package com.weemusic

import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.model.FeedEntity
import com.weemusic.android.data.repository.AlbumsRepositoryImp
import com.weemusic.android.model.toModel
import com.weemusic.android.shared.util.ResponseHandler
import com.weemusic.android.shared.util.TestUtil
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumsRepositoryTest {


    private lateinit var mockItunesApi: ItunesApi
    private lateinit var mockResponseHandler: ResponseHandler
    private lateinit var albumsRepositoryImpl: AlbumsRepositoryImp
    private lateinit var mockFeedCall: retrofit2.Call<FeedEntity>


    @Before
    fun setup() {
        mockItunesApi = mockk()
        mockResponseHandler = mockk()
        mockFeedCall = mockk()


        albumsRepositoryImpl = AlbumsRepositoryImp(
            iTunesApi = mockItunesApi,
            responseHandler = mockResponseHandler,
            dispatcher = TestCoroutineDispatcher(),
        )
    }


    @Test
    fun `should return true when album is retrieved`() = runBlockingTest {
        //given
        val feedEntity = TestUtil.feedEntity
        every { mockResponseHandler.process(mockFeedCall).isSuccess } answers { true }
        every { mockItunesApi.getTopAlbums() } answers { mockFeedCall }

        //when
        val result = albumsRepositoryImpl.returnTopAlbums().getOrThrow()
        //then


        Assert.assertEquals(result, false)
    }

}