import { api } from "./api";

//get requests 
export const getUser = async (username) => {
  try {
    const response = await api.get('/users', {
      params: { username }, 
    });
    return response.data;
  }
  catch (error) {
    throw error;
  }
}


 