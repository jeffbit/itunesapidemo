package com.weemusic.android.feature.albumlist.presentation.model

import com.weemusic.android.model.Album

data class AlbumListUIModel(
    val id: Int,
    val name: String,
    val images: List<String>,
    val title: String,
    val artist: String,
    val priceDbl: Double,
    val price: String
)


fun List<Album>.toAlbumListModel() =
    this.map { it.toAlbumListUIModel() }

fun Album.toAlbumListUIModel() =
    AlbumListUIModel(
        id = id,
        name = name,
        images = images,
        title = title,
        artist = artist,
        priceDbl = priceDbl,
        price = price
    )