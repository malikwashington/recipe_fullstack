package com.ex2.recipe.repository;

import com.ex2.recipe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    int countAllByRecipeId(Long recipeId);

    Optional<Review> findByRecipeIdAndUserId(Long recipeId, Long userId);

    Review findByRecipeId(Long recipeId);

    List<Review> findAllByRecipeId(Long recipeId);
}
