import { createContext, useState, useContext, useEffect } from "react";
import { loginUser, registerUser } from "../api/authApi";
import axios from "axios";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")));

  // ✅ Setup headers on initial mount
  useEffect(() => {
    if (token) {
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }
  }, [token]);

  const setupAuthHeader = (jwt) => {
    if (jwt) {
      axios.defaults.headers.common["Authorization"] = `Bearer ${jwt}`;
      localStorage.setItem("token", jwt);
      setToken(jwt);
    } else {
      delete axios.defaults.headers.common["Authorization"];
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      setToken(null);
      setUser(null);
    }
  };

  const login = async (email, password) => {
    const response = await loginUser({ email, password });
    const { token: jwt, role, id } = response.data;
    setupAuthHeader(jwt);
    const userData = { id, role };
    localStorage.setItem("user", JSON.stringify(userData));
    setUser(userData);
    return response;
  };

  const register = async (userData) => {
    const response = await registerUser(userData);
    const { token: jwt, role, id } = response.data;
    setupAuthHeader(jwt);
    const uData = { id, role };
    localStorage.setItem("user", JSON.stringify(uData));
    setUser(uData);
    return response;
  };

  const logout = () => {
    setupAuthHeader(null);
  };

  const value = { token, user, login, register, logout };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => useContext(AuthContext);
