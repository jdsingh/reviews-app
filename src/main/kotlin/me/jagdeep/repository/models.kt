package me.jagdeep.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedWrapper(
    val feed: Feed
)

@Serializable
data class Feed(
    val updated: Label,
    val entry: List<Entry>,
)

@Serializable
data class Entry(
    val id: Label,
    val author: EntryAuthor,
    val updated: Label,
    @SerialName("im:rating") val imRating: Label,
    val title: Label,
    val content: Content,
)

@Serializable
data class EntryAuthor(
    val name: Label,
)

@Serializable
data class Label(
    val label: String
)

@Serializable
data class Content(
    val label: String,
)
