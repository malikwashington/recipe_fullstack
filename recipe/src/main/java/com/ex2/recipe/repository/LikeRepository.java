package com.ex2.recipe.repository;

import com.ex2.recipe.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository  extends JpaRepository<Like, Long> {
    Long countByRecipeId(Long recipeId);
    boolean existsByRecipeId(Long recipeId);
    Optional<Like> findFirstByRecipeId(Long recipeId);
}
