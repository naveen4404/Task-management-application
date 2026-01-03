//1) Create a context
//2) Provide context to the other components
//3) set state into the context

import { useState } from "react";
import { AuthContext } from "./AuthContextCreate";
import apiClient from "../api/apiClient";

function AuthProvider({ children }) {
  const [authenticated, setAuthenticated] = useState(false);
  const [userEmail, setUserEmail] = useState(null);

  async function login(userName, password) {
    if (!userName || !password) {
      setUserEmail(null);
      return false;
    }

    const getLoggedIn = async function () {
      try {
        const credentials = window.btoa(`${userName}:${password}`);
        await apiClient.post(
          "/api/auth/login",
          {},
          {
            headers: {
              Authorization: `Basic ${credentials}`,
            },
          }
        );
        setUserEmail(userName);
        setAuthenticated(true);
        return true;
      } catch {
        return false;
      }
    };

    return await getLoggedIn();
  }

  function logout() {
    setAuthenticated(false);
    setUserEmail(null);
  }

  return (
    <AuthContext.Provider value={{ authenticated, userEmail, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
export default AuthProvider;
