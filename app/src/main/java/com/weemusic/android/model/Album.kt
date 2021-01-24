package com.weemusic.android.model

import com.weemusic.android.data.model.FeedEntity
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

data class Album(
    val id: Int,
    val name: String,
    val images: List<String>,
    val rights: String,
    val title: String,
    val artist: String,
    val category: String,
    val releaseDate: LocalDate,
    val priceDbl: Double,
    val price: String
)


fun FeedEntity.Feed.Entry.toList(): List<String> {
    val mutableList = mutableListOf<String>()
    this.imImage.forEach { mutableList.add(it.label) }
    return mutableList
}


fun FeedEntity.Feed.Entry.ImReleaseDate.toDate(): LocalDate =
    ZonedDateTime.parse(this.label).toLocalDate()


fun FeedEntity.Feed.Entry.toAlbumModel(): Album {
    return Album(
        id = id.attributes.imId.toInt(),
        name = imName.label,
        images = this.toList(),
        rights = rights.label,
        title = title.label,
        artist = imArtist.label,
        category = category.attributes.label,
        releaseDate = imReleaseDate.toDate(),
        priceDbl = imPrice.attributes.amount.toDouble(),
        price = imPrice.label
    )
}

fun List<FeedEntity.Feed.Entry>.toModel(): List<Album> {
    return this.map { it.toAlbumModel() }
}
