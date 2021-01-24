package com.weemusic.android.feature.albumlist.model

import com.weemusic.android.domain.Album
import org.threeten.bp.LocalDate

data class AlbumListUIModel(
    val id: Int,
    val name: String,
    val images: List<String>,
    val title: String,
    val artist: String,
    val releaseDate: LocalDate
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
        releaseDate = releaseDate
    )