import React from "react";
import styled from "styled-components";
import Sidebar from "../components/layout/Sidebar";
import Header from "../components/layout/Header";
import TrendingPanel from "../components/layout/TrendingPanel";
import { useAuth } from "../contexts/AuthContext";

const HomeContainer = styled.div`
  display: flex;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  margin: 0 auto;
`;

const MainSection = styled.div`
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-grow: 1;
  overflow: hidden;
`;

const MainContent = styled.main`
  flex-grow: 1;
  padding: 20px;
  overflow-y: auto;
  border-left: 1px solid #e1e8ed;
  border-right: 1px solid #e1e8ed;
`;

const Home: React.FC = () => {
  const { user } = useAuth();

  return (
    <HomeContainer>
      <Sidebar />
      <MainSection>
        <Header />
        <ContentWrapper>
          <MainContent>
            <h1>Welcome to ChirpChirp, {user?.username}!</h1>
            <p>This is your home feed. Chirp away!</p>
            {/* Add more content here as needed */}
          </MainContent>
          <TrendingPanel />
        </ContentWrapper>
      </MainSection>
    </HomeContainer>
  );
};

export default Home;
