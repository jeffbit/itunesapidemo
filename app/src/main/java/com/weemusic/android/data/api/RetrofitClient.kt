package com.weemusic.android.data.api

import com.weemusic.android.BuildConfig
import com.weemusic.android.data.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://itunes.apple.com"


    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()

                    interceptors().add(interceptor)
                    return@Interceptor chain.proceed(builder.build())
                })


            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = (HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logging)
            }
        }.build()


    private fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    val iTunesApi: ItunesApi = retrofit().create(ItunesApi::class.java)
}