package com.ex2.recipe.service.review;

import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.model.Review;
import com.ex2.recipe.model.User;
import com.ex2.recipe.repository.RecipeRepository;
import com.ex2.recipe.repository.ReviewRepository;
import com.ex2.recipe.repository.UserRepository;
import com.ex2.recipe.request.ReviewRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService implements ReviewServiceInterface {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void addReview(Long recipeId, ReviewRequest request) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()-> new EntityNotFoundException("Recipe not found."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User Not Found"));

        reviewRepository.findByRecipeIdAndUserId(recipeId, user.getId())
                .ifPresentOrElse(existingReview-> updateReview(existingReview, request),
                        ()-> createNewReview(request, recipe));

        double averageRating = recipe.calculateAverageRating();
        recipe.setAverageRating(averageRating);
        int totalRateCount = recipe.getTotalRateCount();
        recipeRepository.save(recipe);
    }

    private void updateReview(Review existingReview, ReviewRequest request) {
        existingReview.setStars(request.getStars());
        existingReview.setFeedBack(request.getFeedBack());
        reviewRepository.save(existingReview);
    }

    private void createNewReview(ReviewRequest request, Recipe recipe){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User Not Found"));
        Review newReview = new Review();
        newReview.setUser(user); //why???
        newReview.setStars(request.getStars());
        newReview.setFeedBack(request.getFeedBack());
        newReview.setRecipe(recipe);
        newReview.setUser(user);
        reviewRepository.save(newReview);
    }

    @Override
    public void deleteReview(Long recipeId, Long reviewId) {
        reviewRepository.findById(reviewId).ifPresentOrElse(review -> {
            Recipe recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(()-> new EntityNotFoundException("Recipe not found."));
            recipe.getReviews().remove(review);
            recipeRepository.save(recipe);
            reviewRepository.delete(review);
        }, () -> {
            throw new EntityNotFoundException("Review not found.");
        });
    }

    @Override
    public int getReviewCount(Long recipeId) {
        return reviewRepository.countAllByRecipeId(recipeId);
    }
}
