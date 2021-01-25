package com.weemusic.android.shared.util

import com.weemusic.android.data.model.FeedEntity

class TestUtil {

    companion object {


        val feedEntity = FeedEntity(
            feed = FeedEntity.Feed(
                author = FeedEntity.Feed.Author(
                    name = FeedEntity.Feed.Author.Name(
                        label = "iTunes Store"
                    ), uri = FeedEntity.Feed.Author.Uri(label = "http://www.apple.com/itunes/")
                ),
                entry = listOf(
                    FeedEntity.Feed.Entry(
                        category = FeedEntity.Feed.Entry.Category(
                            attributes = FeedEntity.Feed.Entry.Category.Attributes(
                                imId = "6",
                                label = "Country",
                                scheme = "https://music.apple.com/us/genre/music-country/id6?uo=2",
                                term = "Country"
                            )
                        ),
                        id = FeedEntity.Feed.Entry.Id(
                            attributes = FeedEntity.Feed.Entry.Id.Attributes(
                                imId = "1528423147"
                            ),
                            label = "https://music.apple.com/us/album/starting-over/1528423147?uo=2"
                        ),
                        imArtist = FeedEntity.Feed.Entry.ImArtist(
                            attributes = FeedEntity.Feed.Entry.ImArtist.Attributes(
                                href = "https://music.apple.com/us/artist/chris-stapleton/1752134?uo=2"
                            ), label = "Chris Stapleton"
                        ),
                        imContentType = FeedEntity.Feed.Entry.ImContentType(
                            attributes = FeedEntity.Feed.Entry.ImContentType.Attributes(
                                label = "Album",
                                term = "Album"
                            ),
                            imContentType = FeedEntity.Feed.Entry.ImContentType.ImContentType(
                                attributes = FeedEntity.Feed.Entry.ImContentType.ImContentType.Attributes(
                                    label = "Music",
                                    term = "Music"
                                )
                            )
                        ),
                        imImage = listOf(
                            FeedEntity.Feed.Entry.ImImage(
                                attributes = FeedEntity.Feed.Entry.ImImage.Attributes(
                                    height = "55"
                                ),
                                label = "https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/0e/e1/be/0ee1bebf-783b-787e-8f34-0d0df37b3f69/20UMGIM71875.rgb.jpg/55x55bb.png"
                            )
                        ),
                        imItemCount = FeedEntity.Feed.Entry.ImItemCount(label = "14"),
                        imName = FeedEntity.Feed.Entry.ImName(label = "starting over"),
                        imPrice = FeedEntity.Feed.Entry.ImPrice(
                            attributes = FeedEntity.Feed.Entry.ImPrice.Attributes(
                                amount = "9.99000",
                                currency = "USD"
                            ), label = "$9.99"
                        ),
                        imReleaseDate = FeedEntity.Feed.Entry.ImReleaseDate(
                            attributes = FeedEntity.Feed.Entry.ImReleaseDate.Attributes(
                                label = "2020-11-13T00:00:00-07:00"
                            ), label = "2020-11-13T00:00:00-07:00"
                        ),
                        link = FeedEntity.Feed.Entry.Link(
                            attributes = FeedEntity.Feed.Entry.Link.Attributes(
                                href = "https://music.apple.com/us/album/starting-over/1528423147?uo=2",
                                rel = "alternate",
                                type = "text/html"
                            )
                        ),
                        rights = FeedEntity.Feed.Entry.Rights(label = "A Mercury Nashville Release; ℗ 2020 Sound Records, under exclusive license to UMG Recordings, Inc."),
                        title = FeedEntity.Feed.Entry.Title(label = "Starting Over - Chris Stapleton"),
                    )
                ),
                icon = FeedEntity.Feed.Icon(label = "http://itunes.apple.com/favicon.ico"),
                id = FeedEntity.Feed.Id(label = "https://itunes.apple.com/us/rss/topalbums/limit=1/json"),
                link = listOf(
                    FeedEntity.Feed.Link(
                        attributes = FeedEntity.Feed.Link.Attributes(
                            href = "https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewTop?cc=us&id=1&popId=11",
                            rel = "alternate",
                            type = "text/html"
                        )
                    )
                ),
                rights = FeedEntity.Feed.Rights(label = "Copyright 2008 Apple Inc."),
                title = FeedEntity.Feed.Title(label = "iTunes Store: Top Albums"),
                updated = FeedEntity.Feed.Updated(label = "2021-01-24T11:38:45-07:00")
            )
        )


    }

}