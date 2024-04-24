package me.jagdeep.handler

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import me.jagdeep.service.ReviewService

private val logger = KtorSimpleLogger("configureReviewsHandler")

fun Application.configureReviewsHandler(service: ReviewService) {
    routing {
        get("/api/reviews") {
            val hours = call.request.queryParameters["hours"]?.toIntOrNull() ?: 48
            logger.info("Hours: $hours")
            val reviews = service.getReviewsForHours(hours)
            call.respond(reviews)
        }
    }
}
