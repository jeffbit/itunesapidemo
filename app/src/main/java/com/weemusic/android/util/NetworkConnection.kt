package com.weemusic.android.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkConnection @Inject constructor(private val context: Context) {


    fun isNetworkConnected(): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }

                } ?: false
            } else {
                val service = Context.CONNECTIVITY_SERVICE
                val manager: ConnectivityManager =
                    context.getSystemService(service) as ConnectivityManager
                val network = manager.activeNetworkInfo
                return (network != null) && network.isConnected
            }
        }
    }
}