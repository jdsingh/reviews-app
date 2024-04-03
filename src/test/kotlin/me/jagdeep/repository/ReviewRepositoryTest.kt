package me.jagdeep.repository

import kotlinx.coroutines.runBlocking
import me.jagdeep.database.InMemoryReviewDatabase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ReviewRepositoryTest {

    private val reviewsApi = FakeReviewsApi()
    private val reviewDatabase = InMemoryReviewDatabase()
    private val repository = ReviewRepository(reviewsApi, reviewDatabase)

    @Test
    fun `test getSavedReviews with no reviews saved`() = runBlocking {
        // Given
        // No reviews saved

        // When
        val reviews = repository.getSavedReviews()

        // Then
        assertNull(reviews)
    }

    @Test
    fun `test getSavedReviews with reviews saved`(): Unit = runBlocking {
        // Given
        repository.fetchAndSaveReviews()

        // When
        val reviews = repository.getSavedReviews()

        // Then
        assertEquals(fakeReviews, reviews)
    }
}
