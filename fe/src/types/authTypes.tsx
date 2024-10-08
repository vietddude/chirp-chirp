export interface AuthService {
  login: (username: string, password: string) => Promise<{ token: string }>;
  logout: () => void;
  isAuthenticated: () => boolean;
}
