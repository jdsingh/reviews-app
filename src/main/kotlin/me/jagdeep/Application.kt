package me.jagdeep

import io.ktor.server.application.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import me.jagdeep.client.apiClient
import me.jagdeep.database.FileSystemReviewDatabase
import me.jagdeep.handler.configureReviewsHandler
import me.jagdeep.plugins.configureCORS
import me.jagdeep.plugins.configureRouting
import me.jagdeep.plugins.configureSerialization
import me.jagdeep.repository.ReviewRepository
import me.jagdeep.repository.ReviewsApiImpl
import me.jagdeep.service.ReviewService
import java.io.File

fun Application.main() {
    configureSerialization()
    configureCORS()
    configureRouting()
}

fun Application.services() {
    // TODO: Replace with DI
    val reviewApi = ReviewsApiImpl(apiClient)
    val reviewDatabase = FileSystemReviewDatabase(
        file = "reviews.json".let(::File).toPath()
    )
    val reviewRepository = ReviewRepository(
        reviewApi = reviewApi,
        reviewDatabase = reviewDatabase
    )
    val reviewService = ReviewService(reviewRepository)

    configureReviewsHandler(reviewService)
    scheduleReviewsPollingJob(reviewRepository)
}

fun Application.scheduleReviewsPollingJob(reviewRepository: ReviewRepository) {
    environment.monitor.subscribe(ApplicationStarted) {
        log.info("Starting reviews polling job")

        // A better way to schedule a job would be to use a library like Quartz
        // or use a cron job in a production environment
        launch(coroutineContext) {
            while (isActive) {
                reviewRepository.fetchAllReview()
                delay(REVIEWS_POLLING_INTERVAL)
            }
        }
    }
}

const val REVIEWS_POLLING_INTERVAL = 10_000L
