package com.ex2.recipe.service.recipe;

import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.request.CreateRecipeRequest;
import com.ex2.recipe.request.RecipeUpdateRequest;
import com.ex2.recipe.model.User;
import com.ex2.recipe.dto.RecipeDto;

import java.util.List;
import java.util.Set;

public interface RecipeServiceInterface {
    Recipe createRecipe(CreateRecipeRequest request);

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(Long id);

    Recipe updateRecipe(RecipeUpdateRequest request, Long recipeId);

    void deleteRecipe(Long id);

    Set<String> getAllRecipeCategories();

    Set<String> getAllRecipeCuisine();

    static Recipe createRecipe(CreateRecipeRequest request, User user){
        Recipe recipe = new Recipe();
        Recipe createRequest = request.getRecipe();
        recipe.setTitle(createRequest.getTitle());
        recipe.setCuisine(createRequest.getCuisine());
        recipe.setCategory(createRequest.getCategory());
        recipe.setInstruction(createRequest.getInstruction());
        recipe.setDescription(createRequest.getDescription());
        recipe.setPrepTime(createRequest.getPrepTime());
        recipe.setCookTime(createRequest.getCookTime());
        recipe.setIngredients(createRequest.getIngredients());
        recipe.setUser(user);

        return recipe;
    }

    static Recipe updateRecipe(Recipe existingRecipe, RecipeUpdateRequest request){
        existingRecipe.setTitle(createRequest.getTitle());
        existingRecipe.setCuisine(createRequest.getCuisine());
        existingRecipe.setCategory(createRequest.getCategory());
        existingRecipe.setInstruction(createRequest.getInstruction());
        existingRecipe.setDescription(createRequest.getDescription());
        existingRecipe.setPrepTime(createRequest.getPrepTime());
        existingRecipe.setCookTime(createRequest.getCookTime());
        existingRecipe.setIngredients(createRequest.getIngredients());

        return existingRecipe;
    }

    List<RecipeDto> getConvertedRecipes(List<Recipe> recipes);
}
