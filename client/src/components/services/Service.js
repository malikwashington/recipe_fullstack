import { TbReceiptFilled } from "react-icons/tb";
import { api } from "./api";

//get requests
export const getUser = async (username) => {
  try {
    const response = await api.get("/users", {
      params: { username },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const likeRecipe = async (recipeId) => {
  try {
    const res = await api.post(`/likes/recipe/${recipeId}/like`);
    return res.data;
  } catch (e) {
    throw e;
  }
};
