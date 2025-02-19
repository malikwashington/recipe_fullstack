package com.ex2.recipe.service.review;

import com.ex2.recipe.request.ReviewRequest;

public interface ReviewServiceInterface {
    void addReview(Long recipeId, ReviewRequest reviewRequest);
    void deleteReview(Long recipeId, Long reviewId);
    int getReviewCount(Long recipeId);
}
