package com.weemusic.android.shared.util

import android.accounts.NetworkErrorException
import retrofit2.Call
import javax.inject.Inject

class ResponseHandler @Inject constructor(private val networkConnection: NetworkConnection) {

    private val TAG = ResponseHandler::class.java.name

    fun <T> process(call: Call<T>): Result<T> {
        return when (networkConnection.isNetworkConnected()) {
            true -> {
                try {
                    val response = call.execute()
                    when (response.isSuccessful && response.body() != null) {
                        true -> Result.success(response.body()!!)
                        false -> Result.failure(Exception())
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
            false -> Result.failure(NetworkErrorException())
        }
    }
}