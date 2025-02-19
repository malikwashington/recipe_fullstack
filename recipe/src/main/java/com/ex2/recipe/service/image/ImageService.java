package com.ex2.recipe.service.image;

import com.ex2.recipe.dto.ImageDto;
import com.ex2.recipe.model.Image;
import com.ex2.recipe.repository.ImageRepository;
import com.ex2.recipe.service.recipe.RecipeServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceInterface {
    private final ImageRepository imageRepository;
    private final RecipeServiceInterface recipeService;

    @Override
    public Image getImageById(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(()-> new EntityNotFoundException("Image not found."));
    }

    @Override
    public void deleteImage(Long imageId) {
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }

    @Override
    public ImageDto saveImage(Long recipeId, MultipartFile file) {
        return null;
    }
}
