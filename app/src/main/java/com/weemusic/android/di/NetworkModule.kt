package com.weemusic.android.di

import android.content.Context
import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.api.RetrofitClient
import com.weemusic.android.shared.util.NetworkConnection
import com.weemusic.android.shared.util.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit(): ItunesApi {
        return RetrofitClient.iTunesApi
    }

    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context): NetworkConnection {
        return NetworkConnection(context = context)
    }


    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideResponseHandler(networkConnection: NetworkConnection): ResponseHandler {
        return ResponseHandler(networkConnection = networkConnection)
    }


}