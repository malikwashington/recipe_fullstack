package com.ex2.recipe.request;

import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.model.User;
import lombok.Data;

@Data
public class CreateRecipeRequest {
    private Recipe recipe;
    private User user;
}
