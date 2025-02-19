package com.ex2.recipe.service.recipe;

import com.ex2.recipe.dto.RecipeDto;
import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.model.User;
import com.ex2.recipe.repository.ImageRepository;
import com.ex2.recipe.repository.RecipeRepository;
import com.ex2.recipe.repository.ReviewRepository;
import com.ex2.recipe.repository.UserRepository;
import com.ex2.recipe.request.CreateRecipeRequest;
import com.ex2.recipe.request.RecipeUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecipeService implements RecipeServiceInterface {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public Recipe createRecipe(CreateRecipeRequest request) {
        if (request == null || request.getUser() == null) {
            throw new IllegalArgumentException("Invalid request.");
        }

        User user = Optional.ofNullable(userRepository.findByUsername(request.getUser().getUsername()))
                .map(existingUser-> {
                    existingUser.getRecipe().add(request.getRecipe());
                    return existingUser;
                }).orElseGet(()->{
                    User newUser = new User(request.getUser().getUsername());
                    userRepository.save(newUser);
                    return newUser;
                });

        Recipe recipe = RecipeServiceInterface.createRecipe(request, user);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(RecipeUpdateRequest request, Long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        Recipe theRecipe = RecipeServiceInterface.updateRecipe(recipe, request);
        return recipeRepository.save(theRecipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById()
                .orElseThrow(()-> new EntityNotFoundException("Recipe not found."));
    }


    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Set<String> getAllRecipeCategories() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe :: getCategory)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllRecipeCuisine() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe :: getCuisine)
                .collect(Collectors.toSet());
    }

    @Override
    public List<RecipeDto> getConvertedRecipes(List<Recipe> recipes) {
        return recipes.stream().map(this::convertToDto).toList();
    }
}
