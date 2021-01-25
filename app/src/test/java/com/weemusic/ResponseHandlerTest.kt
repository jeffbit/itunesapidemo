package com.weemusic

import android.accounts.NetworkErrorException
import com.weemusic.android.data.model.FeedEntity
import com.weemusic.android.shared.util.NetworkConnection
import com.weemusic.android.shared.util.ResponseHandler
import com.weemusic.android.shared.util.TestUtil
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class ResponseHandlerTest {


    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var responseHandler: ResponseHandler
    private lateinit var mockResponseCall: Response<FeedEntity>
    private lateinit var mockCall: Call<FeedEntity>


    @Before
    fun setup() {
        mockNetworkConnection = mockk()
        mockCall = mockk()
        mockResponseCall = mockk()

        responseHandler = ResponseHandler(networkConnection = mockNetworkConnection)

    }


    @Test
    fun `return false when no network connection`() {
//        given
        every { mockNetworkConnection.isNetworkConnected() } answers { false }

//        when
        val result = responseHandler.process(mockCall)

//        then
        result.onFailure {
            Assert.assertEquals(it, NetworkErrorException())
        }

    }


    @Test
    fun `return result success when process is complete`() {
//        given
        val feedEntity = TestUtil.feedEntity
        every { mockNetworkConnection.isNetworkConnected() } answers { true }
        every { mockResponseCall.isSuccessful } answers { true }
        every { mockResponseCall.body() } answers { feedEntity }
        every { mockCall.execute() } answers { mockResponseCall }

//        when
        val result = responseHandler.process(mockCall)

//        then
        result.onSuccess {
            Assert.assertEquals(Result.success(it), Result.success(feedEntity))
        }
    }


    @Test
    fun `return failure when process is complete`() {
//        given
        val feedEntity = TestUtil.feedEntity
        every { mockNetworkConnection.isNetworkConnected() } answers { true }
        every { mockResponseCall.isSuccessful } answers { false }
        every { mockResponseCall.body() } answers { feedEntity }
        every { mockCall.execute() } answers { mockResponseCall }

//        when
        val result = responseHandler.process(mockCall)

//        then
        result.onFailure {
            Assert.assertEquals(it, Exception())
        }
    }


    @Test
    fun `return exception error when call executes with exception`() {
//        given
        val feedEntity = TestUtil.feedEntity
        every { mockNetworkConnection.isNetworkConnected() } answers { true }
        every { mockResponseCall.isSuccessful } answers { false }
        every { mockResponseCall.body() } answers { feedEntity }
        every { mockCall.execute() } answers { mockResponseCall}

//        when
        val result = responseHandler.process(mockCall).exceptionOrNull()

//        then
       Assert.assertEquals(result, Exception())
        }
    }

