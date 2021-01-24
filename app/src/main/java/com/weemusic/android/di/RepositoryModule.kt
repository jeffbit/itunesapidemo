package com.weemusic.android.di

import com.weemusic.android.data.api.ItunesApi
import com.weemusic.android.data.repository.AlbumsRepository
import com.weemusic.android.data.repository.AlbumsRepositoryImp
import com.weemusic.android.shared.util.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        ItunesApi: ItunesApi,
        networkConnection: NetworkConnection
    ): AlbumsRepository {
        return AlbumsRepositoryImp(
            iTunesApi = ItunesApi,
            networkConnection = networkConnection
        )
    }


}