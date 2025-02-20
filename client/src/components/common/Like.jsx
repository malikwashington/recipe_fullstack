import React from "react";
import { useState, useEffect } from "react";
import { FaThumbsUp } from "react-icons/fa";
import { Card } from "react-bootstrap";
import { getRecipeById, likeRecipe, unlikeRecipe } from "../services/Service";

const Like = ({ recipeId }) => {
  const [likes, setLikes] = useState(0);
  const [hasLiked, setHasLiked] = useState(false);

  useEffect(() => {
    const fetchLikes = async () => {
      try {
        const res = await getRecipeById(recipeId);
        setLikes(res.data.likes);
      } catch (e) {
        console.error(e);
      }
    };
    fetchLikes();
  }, [recipeId]);

  const handleLike = async () => {
    try {
      if (!hasLiked) {
        await likeRecipe(recipeId);
        setLikes(likes + 1);
      } else {
        await unlikeRecipe(recipeId);
        setLikes(likes - 1);
      }
      setHasLiked((prevLike) => !prevLike);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <Card.Text>
      <FaThumbsUp
        className="d-flex align-items-center thumb"
        onClick={handleLike}
        style={{ color: hasLiked ? "blue" : "gray", cursor: "pointer" }}
      />
      <span className="ms-2">{likes}</span>
    </Card.Text>
  );
};

export default Like;
