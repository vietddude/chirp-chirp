import React from "react";
import { ChirpChirpLogin } from "./pages/ChirpChirpLogin";
import { AuthProvider, useAuth } from "./contexts/AuthContext";
import { mockAuthService } from "./services/mockAuth";
import Home from "./pages/Home";

// In the future, you could import a real auth service here
// import { realAuthService } from './services/realAuth';

const AppContent: React.FC = () => {
  const { isAuthenticated, logout } = useAuth();

  return <div>{isAuthenticated ? <Home /> : <ChirpChirpLogin />}</div>;
};

const App: React.FC = () => {
  return (
    <AuthProvider authService={mockAuthService}>
      <AppContent />
    </AuthProvider>
  );
};

export default App;
