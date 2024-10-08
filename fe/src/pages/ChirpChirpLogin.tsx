import React, { useState } from "react";
import styled from "styled-components";
import { useAuth } from "../contexts/AuthContext";
import chirpChirpLogo from "../assets/chirp-chirp-logo.png";
import { Button } from "../components/common/Button";
import { Link } from "../components/common/Link";
import { Input } from "../components/common/Input";

interface ErrorState {
  username?: string;
  password?: string;
  general?: string;
}

const LoginContainer = styled.div`
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica,
    Arial, sans-serif;
  max-width: 300px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
`;

const Logo = styled.img`
  width: 60px; // Adjusted size for the new logo
  margin-bottom: 20px;
`;

const Title = styled.h1`
  font-size: 23px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #00acee; // Updated to match the new color scheme
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

const Links = styled.div`
  margin-top: 20px;
`;

export const ChirpChirpLogin: React.FC = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useAuth();
  const [errors, setErrors] = useState<ErrorState>({});

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setErrors({});

    if (!username) {
      setErrors((prev) => ({ ...prev, username: "Username is required" }));
      return;
    }
    if (!password) {
      setErrors((prev) => ({ ...prev, password: "Password is required" }));
      return;
    }

    try {
      const { token } = await login(username, password);
      console.log("Login successful, token:", token);
    } catch (error) {
      setErrors({ general: "Invalid credentials" });
    }
  };

  return (
    <LoginContainer>
      <Logo src={chirpChirpLogo} alt="Chirp Chirp Logo" />
      <Title>Log in to Chirp Chirp</Title>
      <Form onSubmit={handleSubmit}>
        <Input
          type="text"
          placeholder="Phone, email, or username"
          value={username}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setUsername(e.target.value)
          }
        />
        {errors.username && <ErrorMessage>{errors.username}</ErrorMessage>}

        <Input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setPassword(e.target.value)
          }
        />
        {errors.password && <ErrorMessage>{errors.password}</ErrorMessage>}
        {errors.general && <ErrorMessage>{errors.general}</ErrorMessage>}
        <Button type="submit">Chirp In</Button>
      </Form>
      <Links>
        <Link href="#">Forgot password?</Link>
        <Link href="#">Sign up for Chirp Chirp</Link>
      </Links>
    </LoginContainer>
  );
};

const ErrorMessage = styled.div`
  color: #e0245e;
  margin-bottom: 16px;
`;
