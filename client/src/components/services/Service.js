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

export const unLikeRecipe = async (recipeId) => {
  try {
    const res = await api.put(`/likes/recipe/${recipeId}/unLike`);
    return res.data;
  } catch (e) {
    throw e;
  }
}

export const getRecipeById = async (recipeId) => {
  try {
    const res = await api.get(`/recipes/${recipeId}/recipe`);
    return res.data;
  } catch (e) {
    throw e;
  }
}

export const getAllRecipes = async () => {
  try {
    const res = await api.get(`/recipes`);
    return res.data;
  } catch (e) {
    throw e;
  }
}

export const uploadImage = async ({ recipeId, file }){
  const formData = new FormData()
  formData.append("file", file)
  formData.append("recipeId", recipeId)

  try {
    const res = await api.post('images/upload', formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    })
    return res
  } catch (e) {
    throw e
  }
}

export const updateImage = async ({imageId, file})=>{
  const formData = new FormData()
  formData.append("file", file)

  try {
    const res = await api.post(`images/image/${imageId}/update`, formData)
    return res.data
    }catch(e){
      throw e
    }
  }

