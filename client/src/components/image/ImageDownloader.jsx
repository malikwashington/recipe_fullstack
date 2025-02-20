import React from "react";
import { useEffect, useState } from "react";

const ImageDownloader = ({ recipeId }) => {
  const [recipeImage, setRecipeImage] = useState(null);

  useEffect(() => {
    const fetchRecipeImage = async (id) => {
      try {
        const res = await fetch(
          `http://localhost:9393/api/images/image/download/${id}`
        );
        const blobImage = await res.blob();
        const reader = new Filereader();
        reader.onloadend = () => {
          setRecipeImage(reader.result);
        };
        reader.readAsDataURL(blobImage);
      } catch (e) {
        console.error("Image Fetch Error: ", e);
      }
    };
    if (recipeId) fetchRecipeImage(recipeId);
  }, [recipeId]);

  if (!recipeImage) return null;

  return (
    <div>
      <img src={recipeImage} alt="Recipe Image" />
    </div>
  );
};

export default ImageDownloader;
