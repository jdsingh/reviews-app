package me.jagdeep.handler

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.jagdeep.service.ReviewService

fun Application.configureReviewsHandler(service: ReviewService) {
    routing {
        get("/reviews") {
            val reviews = service.getReviewsForLast48Hours()
            call.respond(reviews)
        }
    }
}
