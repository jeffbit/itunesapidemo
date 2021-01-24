package com.weemusic.android.feature.albumlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weemusic.android.data.repository.AlbumsRepository
import com.weemusic.android.feature.albumlist.presentation.model.AlbumListUIModel
import com.weemusic.android.feature.albumlist.presentation.model.toAlbumListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val topAlbumsRepository: AlbumsRepository) :
    ViewModel() {

    private val _topAlbums = MutableLiveData<List<AlbumListUIModel>>()
    val topAlbums: LiveData<List<AlbumListUIModel>>
        get() = _topAlbums

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var disposable: Disposable? = null


    fun retrieveAlbums() {
        _error.value = null
        topAlbumsRepository.preform().onSuccess { single ->
            disposable = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSuccess { album ->
                    _topAlbums.postValue(album.toAlbumListModel())
                    Timber.d(album[0].artist)
                }.subscribe()
        }.onFailure {
            Timber.e(it)
            _error.postValue(it.message)

        }
    }


    fun retrieveAndSortAlbumByPrice() {
        _error.value = null
        topAlbumsRepository.preform().onSuccess { single ->
            disposable = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSuccess { album ->
                    _topAlbums.postValue(album.toAlbumListModel().sortedBy { it.priceDbl })
                    Timber.d(album[0].artist)
                }.doOnError {
                    _error.postValue(it.message)
                    Timber.e(it)
                }.subscribe()
        }.onFailure {
            Timber.e(it)
            _error.postValue(it.message)

        }

    }

    fun retrieveAndSortAlbumByArtist() {
        _error.value = null
        topAlbumsRepository.preform().onSuccess { single ->
            disposable = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSuccess { album ->
                    _topAlbums.postValue(album.toAlbumListModel().sortedBy { it.artist })
                    Timber.d(album[0].artist)
                }.doOnError {
                    _error.postValue(it.message)
                    Timber.e(it)
                }.subscribe()
        }.onFailure {
            Timber.e(it)
            _error.postValue(it.message)

        }

    }

    fun retrieveAndSortAlbumByAlbum() {
        _error.value = null
        topAlbumsRepository.preform().onSuccess { single ->
            disposable = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSuccess { album ->
                    _topAlbums.postValue(album.toAlbumListModel().sortedBy { it.name })
                    Timber.d(album[0].artist)
                }.doOnError {
                    _error.postValue(it.message)
                    Timber.e(it)
                }.subscribe()
        }.onFailure {
            Timber.e(it)
            _error.postValue(it.message)

        }

    }


    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}

