package com.ex2.recipe.service.image;

import com.ex2.recipe.dto.ImageDto;
import com.ex2.recipe.model.Image;
import com.ex2.recipe.model.Recipe;
import com.ex2.recipe.repository.ImageRepository;
import com.ex2.recipe.service.recipe.RecipeServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.SQL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;

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
        imageRepository.findById(imageId).ifPresentOrElse(imageRepository::delete, () ->{
            throw new EntityNotFoundException("Image not found.");
        });
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try{
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        }catch(IOException | SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ImageDto saveImage(Long recipeId, MultipartFile file) {
        Recipe recipe = recipeService.getRecipeById(recipeId);

        try{
            Image image = new Image();

            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            image.setRecipe(recipe);

            String buildDownloadUrl = "/api/images/image/download/" ;
            String downloadUrl = buildDownloadUrl+image.getId();
            image.setDownloadUrl(downloadUrl);

            Image savedImage = imageRepository.save(image);
            savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
            imageRepository.save(savedImage);

            ImageDto imageDto = new ImageDto();
            imageDto.setId (savedImage.getId());
            imageDto.setFileName(savedImage.getFileName());
            imageDto.setDownloadUrl(savedImage.getDownloadUrl());

            return imageDto;
        }catch(SQLException | IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
