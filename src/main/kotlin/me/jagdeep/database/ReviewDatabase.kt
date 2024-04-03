package me.jagdeep.database

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.jagdeep.domain.AppReviews
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

/**
 * A database for storing and retrieving app reviews.
 */
interface ReviewDatabase {
    suspend fun getReviews(): AppReviews?
    suspend fun saveReviews(reviews: AppReviews)
}

/**
 * A file system implementation of [ReviewDatabase].
 */
class FileSystemReviewDatabase(private val file: Path) : ReviewDatabase {

    private val json = Json { prettyPrint = true }

    override suspend fun getReviews(): AppReviews? {
        return file.exists().let { exists ->
            if (exists) {
                val content = file.readText()
                return json.decodeFromString<AppReviews>(content)
            } else {
                null
            }
        }
    }

    override suspend fun saveReviews(reviews: AppReviews) {
        val content = json.encodeToString(reviews)
        file.writeText(content)
    }
}
