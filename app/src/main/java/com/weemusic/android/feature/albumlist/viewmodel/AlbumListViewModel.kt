package com.weemusic.android.feature.albumlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weemusic.android.data.repository.AlbumsRepository
import com.weemusic.android.feature.albumlist.model.AlbumListUIModel
import com.weemusic.android.feature.albumlist.model.toAlbumListUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val topAlbumsRepositoryImp: AlbumsRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel


    private val _topAlbums = MutableLiveData<List<AlbumListUIModel>>()
    val topAlbums: LiveData<List<AlbumListUIModel>>
        get() = _topAlbums


    fun retrieveAlbums() {
        topAlbumsRepositoryImp.preform().onSuccess { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    _topAlbums.postValue(response.map { it.toAlbumListUIModel() })
                    Timber.e(response.toString())
                }
        }.onFailure {
            Timber.e(it)
        }
    }
}

