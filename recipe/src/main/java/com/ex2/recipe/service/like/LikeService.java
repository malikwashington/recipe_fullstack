package com.ex2.recipe.service.like;

import com.ex2.recipe.model.Like;
import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.repository.LikeRepository;
import com.ex2.recipe.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService implements LikeServiceInterface {
    private final LikeRepository likeRepository;
    private final RecipeRepository recipeRepository;


    @Override
    public int likeRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).map(recipe->{
            if(!likeRepository.existsByRecipeId((recipe.getId()))){
                Like like = new Like();
                likeRepository.save(like);
            }
            recipe.setLikeCount(recipe.getLikeCount()+1);
            return recipeRepository.save(recipe).getLikeCount();
        }).orElseThrow(()-> new EntityNotFoundException("Recipe not found."));
    }

    @Override
    public int unLikeRecipe(Long recipeId) {
        return likeRepository.findFirstByRecipeId(recipeId).map(like->{
            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
            if(recipe.getLikeCount()>0){
                recipe.setLikeCount(recipe.getLikeCount()-1);
                recipeRepository.save(recipe);
            }else{
                throw new IllegalArgumentException("Recipe has no likes.");
            }
            return recipe.getLikeCount();
        }).orElseThrow(()-> new EntityNotFoundException("No likes found for recipe"));
    }

    @Override
    public Long getLikesCount(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .map(recipe-> likeRepository.countByRecipeId(recipe.getId())).orElse(0L);
    }
}
