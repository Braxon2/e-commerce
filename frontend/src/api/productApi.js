import apiClient from "./axiosConfig";

export const getProducts = (page = 0, size = 10) => {
  return apiClient.get(`/api/products?page=${page}&size=${size}`);
};

export const getProductById = (id) => {
  return apiClient.get(`/api/products/${id}`);
};
