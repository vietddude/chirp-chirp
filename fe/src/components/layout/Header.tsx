import React from "react";
import styled from "styled-components";
import { FaSearch } from "react-icons/fa";

const HeaderContainer = styled.header`
  position: sticky;
  top: 0;
  background-color: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid #e1e8ed;
  padding: 10px 20px;
  display: flex;
  align-items: center;
  z-index: 1000;
`;

const SearchBar = styled.div`
  display: flex;
  align-items: center;
  background-color: #ebeef0;
  border-radius: 20px;
  padding: 8px 15px;
  width: 100%;
  max-width: 350px;
`;

const SearchInput = styled.input`
  border: none;
  background: transparent;
  margin-left: 10px;
  font-size: 16px;
  width: 100%;
  &:focus {
    outline: none;
  }
`;

const Header: React.FC = () => {
  return (
    <HeaderContainer>
      <SearchBar>
        <FaSearch color="#657786" />
        <SearchInput placeholder="Search ChirpChirp" />
      </SearchBar>
    </HeaderContainer>
  );
};

export default Header;
