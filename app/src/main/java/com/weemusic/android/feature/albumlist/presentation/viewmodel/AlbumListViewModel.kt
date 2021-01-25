package com.weemusic.android.feature.albumlist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weemusic.android.data.repository.AlbumsRepository
import com.weemusic.android.feature.albumlist.presentation.model.AlbumListUIModel
import com.weemusic.android.feature.albumlist.presentation.model.toAlbumListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val topAlbumsRepository: AlbumsRepository) :
    ViewModel() {

    private val _topAlbums = MutableLiveData<List<AlbumListUIModel>>()
    val topAlbums: LiveData<List<AlbumListUIModel>>
        get() = _topAlbums

    private val _errorBoolean = MutableLiveData<Boolean>()
    val errorBoolean: LiveData<Boolean>
        get() = _errorBoolean

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    fun retrieveAlbums() {
        _error.postValue(null)
        _progress.postValue(true)
        _errorBoolean.postValue(false)
        viewModelScope.launch {
            topAlbumsRepository.returnTopAlbums()
                .onSuccess {
                    _topAlbums.postValue(it.toAlbumListModel())
                    _progress.postValue(false)

                    Timber.d(it.toString())
                }
                .onFailure {
                    Timber.e(it)
                    _errorBoolean.postValue(true)
                    _error.postValue(it.message)
                    _progress.postValue(false)


                }
        }
    }


    fun retrieveAndSortAlbumByPrice() {
        _progress.postValue(true)
        _error.postValue(null)
        viewModelScope.launch {
            topAlbumsRepository.returnTopAlbums()
                .onSuccess {
                    _topAlbums.postValue(it.toAlbumListModel().sortedBy { it.priceDbl })
                    _progress.postValue(false)

                }
                .onFailure {
                    Timber.e(it)
                    _errorBoolean.postValue(true)
                    _error.postValue(it.message)
                    _progress.postValue(false)

                }
        }

    }

    fun retrieveAndSortAlbumByArtist() {
        _progress.postValue(true)
        _error.postValue(null)
        viewModelScope.launch {
            topAlbumsRepository.returnTopAlbums()
                .onSuccess {
                    _topAlbums.postValue(it.toAlbumListModel().sortedBy { it.artist })
                    _progress.postValue(false)

                }
                .onFailure {
                    Timber.e(it)
                    _errorBoolean.postValue(true)
                    _error.postValue(it.message)
                    _progress.postValue(false)

                }
        }

    }

    fun retrieveAndSortAlbumByAlbum() {
        _progress.postValue(true)
        _error.postValue(null)
        viewModelScope.launch {
            topAlbumsRepository.returnTopAlbums()
                .onSuccess {
                    _topAlbums.postValue(it.toAlbumListModel().sortedBy { it.title })
                    _progress.postValue(false)

                }
                .onFailure {
                    Timber.e(it)
                    _errorBoolean.postValue(true)
                    _error.postValue(it.message)
                    _progress.postValue(false)

                }
        }

    }


}

