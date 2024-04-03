package me.jagdeep.database

import me.jagdeep.domain.AppReviews

/**
 * A database implementation that does not store reviews.
 */
class InMemoryReviewDatabase : ReviewDatabase {

    private var reviews: AppReviews? = null

    override suspend fun getReviews(): AppReviews? = reviews

    override suspend fun saveReviews(reviews: AppReviews) {
        this.reviews = reviews
    }
}
