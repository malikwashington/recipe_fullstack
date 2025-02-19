package com.ex2.recipe.service.like;

public interface LikeServiceInterface {
    int likeRecipe(Long recipeId);
    int unLikeRecipe(Long recipeId);
    Long getLikesCount(Long recipeId);
}
