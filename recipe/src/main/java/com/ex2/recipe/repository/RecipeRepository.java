package com.ex2.recipe.repository;

import com.ex2.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //this is empty on purpose. it inherits the methods from the parent class
}
