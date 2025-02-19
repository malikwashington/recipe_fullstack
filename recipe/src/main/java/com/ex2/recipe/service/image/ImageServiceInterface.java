package com.ex2.recipe.service.image;

import com.ex2.recipe.dto.ImageDto;
import com.ex2.recipe.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {
    Image getImageById(Long imageId);
    void deleteImage(Long imageId);
    void updateImage(MultipartFile file, Long imageId);
    ImageDto saveImage(Long recipeId, MultipartFile file);
}
