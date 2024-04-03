package me.jagdeep

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import me.jagdeep.database.InMemoryReviewDatabase
import me.jagdeep.domain.Review
import me.jagdeep.handler.configureReviewsHandler
import me.jagdeep.repository.FakeReviewsApi
import me.jagdeep.repository.ReviewRepository
import me.jagdeep.repository.fakeReviews
import me.jagdeep.service.ReviewService
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        environment {
            config = ApplicationConfig("application-custom.conf")
        }
        application {
            main()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun `testReviews - no reviews`() = testApplication {
        environment {
            config = ApplicationConfig("application-custom.conf")
        }
        application {
            main()

            val reviewApi = FakeReviewsApi()
            val reviewDatabase = InMemoryReviewDatabase()
            val reviewRepository = ReviewRepository(
                reviewApi = reviewApi,
                reviewDatabase = reviewDatabase
            )
            val reviewService = ReviewService(reviewRepository)
            configureReviewsHandler(reviewService)
        }

        client.get("/reviews").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("[\n]", bodyAsText())
        }
    }

    @Test
    fun `testReviews - with reviews`() = testApplication {
        environment {
            config = ApplicationConfig("application-custom.conf")
        }
        application {
            main()

            val reviewApi = FakeReviewsApi()
            val reviewDatabase = InMemoryReviewDatabase()
            val reviewRepository = ReviewRepository(
                reviewApi = reviewApi,
                reviewDatabase = reviewDatabase
            )
            val reviewService = ReviewService(reviewRepository)
            configureReviewsHandler(reviewService)

            // Fetch and save fake reviews
            runBlocking { reviewRepository.fetchAndSaveReviews() }
        }

        client.get("/reviews").apply {
            assertEquals(HttpStatusCode.OK, status)
            val reviews = bodyAsText().let {
                Json.decodeFromString<List<Review>>(it)
            }
            assertEquals(2, reviews.size)
            assertEquals(fakeReviews.reviews.take(2), reviews)
        }
    }
}
