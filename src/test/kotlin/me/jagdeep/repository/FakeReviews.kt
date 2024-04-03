package me.jagdeep.repository

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import me.jagdeep.domain.AppReviews
import me.jagdeep.domain.Review
import kotlin.time.Duration.Companion.hours

val fakeReviews = AppReviews(
    updatedAt = Instant.parse("2022-01-01T00:00:00Z"),
    reviews = listOf(
        Review(
            id = "1",
            title = "Great app",
            content = "I love this app!",
            author = "Alice",
            rating = 5,
            datetime = Clock.System.now().minus(1.hours)
        ),
        Review(
            id = "2",
            title = "Needs improvement",
            content = "This app could be better.",
            author = "Bob",
            rating = 3,
            datetime = Clock.System.now().minus(2.hours)
        ),
        Review(
            id = "3",
            title = "Terrible",
            content = "I hate this app!",
            author = "Charlie",
            rating = 1,
            datetime = Clock.System.now().minus(50.hours)
        )
    )
)
