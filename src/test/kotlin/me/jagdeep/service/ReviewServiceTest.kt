package me.jagdeep.service

import kotlinx.coroutines.runBlocking
import me.jagdeep.database.InMemoryReviewDatabase
import me.jagdeep.repository.FakeReviewsApi
import me.jagdeep.repository.ReviewRepository
import me.jagdeep.repository.fakeReviews
import kotlin.test.Test
import kotlin.test.assertEquals

class ReviewServiceTest {

    private val reviewDatabase = InMemoryReviewDatabase()
    private val reviewApi = FakeReviewsApi()
    private val reviewRepository = ReviewRepository(reviewApi, reviewDatabase)
    private val reviewService = ReviewService(reviewRepository)

    @Test
    fun `test getReviewsForLast48Hours with 2 reviews saved`() = runBlocking {
        // Given
        reviewRepository.fetchAndSaveReviews()

        // When
        val reviews = reviewService.getReviewsForLast48Hours()

        // Then
        assertEquals(2, reviews.size)
        assertEquals(fakeReviews.reviews.take(2), reviews)
    }

    @Test
    fun `test getReviewsForLast48Hours with no reviews saved`() = runBlocking {
        // Given
        // No reviews saved

        // When
        val reviews = reviewService.getReviewsForLast48Hours()

        // Then
        assertEquals(0, reviews.size)
    }
}
