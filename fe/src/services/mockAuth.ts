import { AuthService } from "../types/authTypes";

const MOCK_USER = { username: "chirpuser", password: "chirp123" };

export const mockAuthService: AuthService = {
  login: (username: string, password: string): Promise<{ token: string }> => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        if (
          username === MOCK_USER.username &&
          password === MOCK_USER.password
        ) {
          const token =
            "fake-jwt-token-" + Math.random().toString(36).substr(2);
          localStorage.setItem("authToken", token);
          resolve({ token });
        } else {
          reject(new Error("Invalid credentials"));
        }
      }, 500);
    });
  },

  logout: (): void => {
    localStorage.removeItem("authToken");
  },

  isAuthenticated: (): boolean => {
    return !!localStorage.getItem("authToken");
  },
};
