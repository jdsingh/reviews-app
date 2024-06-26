package me.jagdeep.service

import kotlinx.datetime.Clock
import me.jagdeep.domain.Review
import me.jagdeep.repository.ReviewRepository
import kotlin.time.Duration.Companion.hours

/**
 * A service for fetching reviews for the last 48 hours.
 */
class ReviewService(private val reviewRepository: ReviewRepository) {

    suspend fun getReviewsForHours(hours: Int): List<Review> {
        val reviews = reviewRepository.getSavedReviews()?.reviews ?: emptyList()
        val now = Clock.System.now()
        val last48Hours = now.minus(hours.hours)
        return reviews.filter { it.datetime > last48Hours }
    }
}
