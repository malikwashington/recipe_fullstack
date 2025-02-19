package com.ex2.recipe.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private String title;
    private String instruction;
    private String prepTime;
    private String cookTime;
    private String category;
    private Long likeCount;
    private double averageRating;
    private int totalRateCount;
    private String description;
    private String cuisine;
    private List<String> ingredients;
    private ImageDto imageDto;
    private UserDto user;
    private List<ReviewDto> reviews;
}
