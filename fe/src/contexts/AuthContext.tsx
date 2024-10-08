import React, { createContext, useContext, useState, useEffect } from "react";
import { AuthService } from "../types/authTypes";

interface AuthContextType {
  isAuthenticated: boolean;
  login: (username: string, password: string) => Promise<{ token: string }>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

interface AuthProviderProps {
  children: React.ReactNode;
  authService: AuthService;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({
  children,
  authService,
}) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    setIsAuthenticated(authService.isAuthenticated());
  }, [authService]);

  const login = async (username: string, password: string) => {
    const result = await authService.login(username, password);
    setIsAuthenticated(true);
    return result;
  };

  const logout = () => {
    authService.logout();
    setIsAuthenticated(false);
  };

  const value = {
    isAuthenticated,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
