package com.ex2.recipe.controller;

import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.request.CreateRecipeRequest;
import com.ex2.recipe.service.recipe.RecipeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeServiceInterface recipeService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(CreateRecipeRequest request) {
        return ResponseEntity.status(201).body(recipeService.createRecipe(request));
    }

    @PostMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}
