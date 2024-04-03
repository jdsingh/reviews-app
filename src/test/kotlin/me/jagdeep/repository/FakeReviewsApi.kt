package me.jagdeep.repository

import me.jagdeep.domain.AppReviews

class FakeReviewsApi : ReviewsApi {

    override suspend fun fetchReviews(): Result<AppReviews> {
        return Result.success(fakeReviews)
    }
}
