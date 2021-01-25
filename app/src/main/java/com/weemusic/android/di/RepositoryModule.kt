package com.weemusic.android.di

import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.repository.AlbumsRepository
import com.weemusic.android.data.repository.AlbumsRepositoryImp
import com.weemusic.android.shared.util.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        ItunesApi: ItunesApi,
        responseHandler: ResponseHandler,
        dispatcher: CoroutineDispatcher
    ): AlbumsRepository {
        return AlbumsRepositoryImp(
            iTunesApi = ItunesApi,
            responseHandler = responseHandler,
            dispatcher = dispatcher
        )
    }


}