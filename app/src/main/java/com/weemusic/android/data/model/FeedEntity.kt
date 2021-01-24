package com.weemusic.android.data.model


import com.google.gson.annotations.SerializedName

data class FeedEntity(
    val feed: Feed
) {
    data class Feed(
        val author: Author,
        val entry: List<Entry>,
        val icon: Icon,
        val id: Id,
        val link: List<Link>,
        val rights: Rights,
        val title: Title,
        val updated: Updated
    ) {
        data class Author(
            val name: Name,
            val uri: Uri
        ) {
            data class Name(
                val label: String
            )

            data class Uri(
                val label: String
            )
        }

        data class Entry(
            val category: Category,
            val id: Id,
            @SerializedName("im:artist")
            val imArtist: ImArtist,
            @SerializedName("im:contentType")
            val imContentType: ImContentType,
            @SerializedName("im:image")
            val imImage: List<ImImage>,
            @SerializedName("im:itemCount")
            val imItemCount: ImItemCount,
            @SerializedName("im:name")
            val imName: ImName,
            @SerializedName("im:price")
            val imPrice: ImPrice,
            @SerializedName("im:releaseDate")
            val imReleaseDate: ImReleaseDate,
            val link: Link,
            val rights: Rights,
            val title: Title
        ) {
            data class Category(
                val attributes: Attributes
            ) {
                data class Attributes(
                    @SerializedName("im:id")
                    val imId: String,
                    val label: String,
                    val scheme: String,
                    val term: String
                )
            }

            data class Id(
                val attributes: Attributes,
                val label: String
            ) {
                data class Attributes(
                    @SerializedName("im:id")
                    val imId: String
                )
            }

            data class ImArtist(
                val attributes: Attributes,
                val label: String
            ) {
                data class Attributes(
                    val href: String
                )
            }

            data class ImContentType(
                val attributes: Attributes,
                @SerializedName("im:contentType")
                val imContentType: ImContentType
            ) {
                data class Attributes(
                    val label: String,
                    val term: String
                )

                data class ImContentType(
                    val attributes: Attributes
                ) {
                    data class Attributes(
                        val label: String,
                        val term: String
                    )
                }
            }

            data class ImImage(
                val attributes: Attributes,
                val label: String
            ) {
                data class Attributes(
                    val height: String
                )
            }

            data class ImItemCount(
                val label: String
            )

            data class ImName(
                val label: String
            )

            data class ImPrice(
                val attributes: Attributes,
                val label: String
            ) {
                data class Attributes(
                    val amount: String,
                    val currency: String
                )
            }

            data class ImReleaseDate(
                val attributes: Attributes,
                val label: String
            ) {
                data class Attributes(
                    val label: String
                )
            }

            data class Link(
                val attributes: Attributes
            ) {
                data class Attributes(
                    val href: String,
                    val rel: String,
                    val type: String
                )
            }

            data class Rights(
                val label: String
            )

            data class Title(
                val label: String
            )
        }

        data class Icon(
            val label: String
        )

        data class Id(
            val label: String
        )

        data class Link(
            val attributes: Attributes
        ) {
            data class Attributes(
                val href: String,
                val rel: String,
                val type: String
            )
        }

        data class Rights(
            val label: String
        )

        data class Title(
            val label: String
        )

        data class Updated(
            val label: String
        )
    }
}