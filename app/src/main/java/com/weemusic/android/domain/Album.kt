package com.weemusic.android.domain

import com.weemusic.android.data.model.FeedEntity
import org.threeten.bp.LocalDate

data class Album(
    val id: Int,
    val name: String,
    val images: List<String>,
    val rights: String,
    val title: String,
    val artist: String,
    val category: String,
    val releaseDate: LocalDate
)


fun FeedEntity.Feed.Entry.toList(): List<String> {
    val mutableList = mutableListOf<String>()
    this.imImage.forEach { mutableList.add(it.label) }
    return mutableList
}


fun FeedEntity.Feed.Entry.ImReleaseDate.toDate(): LocalDate =
    LocalDate.parse(this.label)


fun FeedEntity.Feed.Entry.toAlbumModel(): Album {
    return Album(
        id = id.label.toInt(),
        name = imName.label,
        images = this.toList(),
        rights = rights.label,
        title = title.label,
        artist = imArtist.label,
        category = category.attributes.label,
        releaseDate = imReleaseDate.toDate()
    )
}

fun List<FeedEntity.Feed.Entry>.toModel(): List<Album> {
    return this.map { it.toAlbumModel() }
}
