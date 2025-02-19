package com.ex2.recipe.controller;

import com.ex2.recipe.dto.RecipeDto;
import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.request.CreateRecipeRequest;
import com.ex2.recipe.request.RecipeUpdateRequest;
import com.ex2.recipe.service.recipe.RecipeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeServiceInterface recipeService;

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody CreateRecipeRequest request) {
        Recipe recipe = recipeService.createRecipe(request);
        return ResponseEntity.ok(recipeService.convertToDto(recipe));
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes(){
        List<Recipe> recipes = recipeService.getAllRecipes();
        List<RecipeDto> recipeDto = recipeService.getConvertedRecipes(recipes);
        return ResponseEntity.ok(recipeDto);
    }

    @GetMapping("/{recipeId}/recipe")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long recipeId){
        RecipeDto recipe = recipeService.convertToDto(recipeService.getRecipeById(recipeId));
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{recipeId}/update")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeUpdateRequest request){
        Recipe updatedRecipe = recipeService.updateRecipe(request, recipeId);
        RecipeDto recipeDto = recipeService.convertToDto(updatedRecipe);
        return ResponseEntity.ok(recipeDto);
    }

    @DeleteMapping("/{recipeId}/delete")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllRecipeCategories(){
        Set<String> categories = recipeService.getAllRecipeCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/cuisines")
    public ResponseEntity<Set<String>> getAllRecipeCuisine(){
        Set<String> cuisines = recipeService.getAllRecipeCuisine();
        return ResponseEntity.ok(cuisines);
    }
}
