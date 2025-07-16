import React, { createContext, useState, useContext, useEffect } from "react";
import {
  getCart as fetchCart,
  addToCart as addItem,
  updateCartItem,
  deleteCartItem,
} from "../api/cartApi.js";
import { useAuth } from "./AuthContext.jsx";

const CartContext = createContext(null);

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState({ items: [], total: 0 });
  const { token } = useAuth();

  const getCart = async () => {
    try {
      const response = await fetchCart();
      setCart(response.data);
    } catch (error) {
      console.error("Failed to fetch cart", error);
    }
  };

  useEffect(() => {
    if (token) {
      getCart();
    } else {
      setCart({ items: [], total: 0 });
    }
  }, [token]);

  const addToCart = async (productId, quantity) => {
    try {
      await addItem({ productId, quantity });
      getCart();
    } catch (error) {
      console.error("Failed to add to cart", error);
    }
  };

  const updateItemQuantity = async (itemId, quantity) => {
    try {
      await updateCartItem(itemId, quantity);
      getCart();
    } catch (error) {
      console.error("Failed to update item", error);
    }
  };

  const removeItem = async (itemId) => {
    try {
      await deleteCartItem(itemId);
      getCart();
    } catch (error) {
      console.error("Failed to remove item", error);
    }
  };

  const value = {
    cart,
    addToCart,
    updateItemQuantity,
    removeItem,
    cartItemCount: cart.items.length,
  };

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};

export const useCart = () => useContext(CartContext);
