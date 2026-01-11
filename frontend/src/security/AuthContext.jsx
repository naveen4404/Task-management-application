//1) Create a context
//2) Provide context to the other components
//3) set state into the context

import { useState } from "react";

import { AuthContext } from "./AuthContextCreate";
import apiClient from "../api/apiClient";

function AuthProvider({ children }) {
  const [authenticated, setAuthenticated] = useState(() => {
    if (localStorage.getItem("authToken")) {
      return true;
    } else {
      return false;
    }
  });

  //Login
  async function login(email, password) {
    if (!email || !password) {
      return false;
    }

    const getLoggedIn = async function () {
      try {
        const response = await apiClient.post("/api/v1/auth/login", {
          email,
          password,
        });

        setAuthenticated(true);
        localStorage.setItem("authToken", response.data.token);
        return true;
      } catch {
        return false;
      }
    };

    return await getLoggedIn();
  }

  // logout
  function logout() {
    setAuthenticated(false);
    localStorage.clear();
  }
  return (
    <AuthContext.Provider value={{ authenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
export default AuthProvider;
