package me.jagdeep.repository

import io.ktor.util.logging.*
import me.jagdeep.database.ReviewDatabase
import me.jagdeep.domain.AppReviews

/**
 * Repository for reviews.
 *
 * This class handles the logic for fetching and saving reviews.
 */
class ReviewRepository(
    private val reviewApi: ReviewsApi,
    private val reviewDatabase: ReviewDatabase,
) {

    private val logger = KtorSimpleLogger("ReviewRepository")

    suspend fun getSavedReviews(): AppReviews? {
        return reviewDatabase.getReviews()
    }

    suspend fun fetchAndSaveReviews() {
        return reviewApi.fetchReviews()
            .fold(onSuccess = { appReviews ->
                reviewDatabase.saveReviews(appReviews)
                logger.info("Fetched and saved ${appReviews.reviews.size} reviews")
            }, onFailure = { exception ->
                logger.error("Failed to fetch reviews", exception)
            })
    }
}
