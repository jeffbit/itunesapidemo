package com.weemusic.android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.api.RetrofitClient
import com.weemusic.android.util.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit(): ItunesApi{
        return RetrofitClient.iTunesApi
    }

    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context): NetworkConnection {
        return NetworkConnection(context = context)
    }

}