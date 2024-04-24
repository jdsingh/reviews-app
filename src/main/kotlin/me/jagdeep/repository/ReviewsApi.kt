package me.jagdeep.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import me.jagdeep.domain.AppReviews
import me.jagdeep.domain.Review

interface ReviewsApi {
    suspend fun fetchReviews(page: Int): Result<AppReviews>
}

/**
 * Fetches reviews from the iTunes API.
 */
class ReviewsApiImpl(private val apiClient: HttpClient) : ReviewsApi {

    override suspend fun fetchReviews(page: Int): Result<AppReviews> = runCatching {
        val response = apiClient.get {
            url("${BASE_URL}/id=${APP_ID}/sortBy=${SORT_BY}/page=${page}/json")
        }

        val body = response.body<String>()
        val json = Json { ignoreUnknownKeys = true }
        val feedWrapper = json.decodeFromString<FeedWrapper>(body)

        return Result.success(AppReviews(
            updatedAt = Instant.parse(feedWrapper.feed.updated.label),
            reviews = feedWrapper.feed.entry.map { entry ->
                Review(
                    id = entry.id.label,
                    title = entry.title.label,
                    content = entry.content.label,
                    author = entry.author.name.label,
                    rating = entry.imRating.label.toIntOrNull() ?: 0,
                    datetime = Instant.parse(entry.updated.label)
                )
            }
        ))
    }

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/us/rss/customerreviews"
        private const val APP_ID = "447188370"
        private const val SORT_BY = "mostRecent"
    }
}
