package me.jagdeep.domain

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class AppReviews(
    val updatedAt: Instant,
    val reviews: List<Review>
)

@Serializable
data class Review(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
    val rating: Int,
    val datetime: Instant,
)
