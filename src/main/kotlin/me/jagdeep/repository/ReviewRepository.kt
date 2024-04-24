package me.jagdeep.repository

import io.ktor.util.logging.*
import kotlinx.datetime.Clock
import me.jagdeep.database.ReviewDatabase
import me.jagdeep.domain.AppReviews
import me.jagdeep.domain.Review

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
        return reviewApi.fetchReviews(1)
            .fold(onSuccess = { appReviews ->
                reviewDatabase.saveReviews(appReviews)
                logger.info("Fetched and saved ${appReviews.reviews.size} reviews")
            }, onFailure = { exception ->
                logger.error("Failed to fetch reviews", exception)
            })
    }

    suspend fun fetchAllReview() {
        val reviews = mutableListOf<Review>()
        for (page in 1..10) {
            reviewApi.fetchReviews(page)
                .fold(onSuccess = { appReviews ->
                    reviews.addAll(appReviews.reviews)
                    logger.info("Fetched and saved ${appReviews.reviews.size} reviews")
                }, onFailure = { exception ->
                    logger.error("Failed to fetch reviews", exception)
                })
        }

        reviewDatabase.saveReviews(
            AppReviews(
                updatedAt = Clock.System.now(),
                reviews = reviews
            )
        )
    }
}
