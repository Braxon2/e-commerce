import apiClient from "./axiosConfig";

export const getCart = () => {
  return apiClient.get("/api/cart");
};

export const addToCart = (itemData) => {
  const { productId, quantity } = itemData;
  return apiClient.post("/api/cart/add", { productId, quantity });
};

export const updateCartItem = (itemId, quantity) => {
  return apiClient.put(`/api/cart/item/${itemId}?quantity=${quantity}`);
};

export const deleteCartItem = (itemId) => {
  return apiClient.delete(`/api/cart/item/${itemId}`);
};
