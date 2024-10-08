import React from "react";
import { useAuth } from "../../contexts/AuthContext";
import styled from "styled-components";
import {
  FaHome,
  FaHashtag,
  FaBell,
  FaEnvelope,
  FaBookmark,
  FaList,
  FaUser,
  FaEllipsisH,
} from "react-icons/fa";

const SidebarContainer = styled.aside`
  width: 275px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  position: sticky;
  top: 0;
  height: 100vh;
  overflow-y: auto;
`;

const Logo = styled.div`
  margin-bottom: 20px;
  color: #1da1f2;
  font-size: 30px;
`;

const Nav = styled.nav`
  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    margin-bottom: 25px;
  }

  a {
    text-decoration: none;
    color: #000;
    font-size: 20px;
    display: flex;
    align-items: center;

    &:hover {
      color: #1da1f2;
      font-weight: bold;
    }

    svg {
      margin-right: 20px;
      width: 24px;
      height: 24px;
    }
  }
`;

const ChirpButton = styled.button`
  background-color: #1da1f2;
  color: white;
  border: none;
  border-radius: 30px;
  padding: 15px 0;
  font-size: 18px;
  font-weight: bold;
  cursor: pointer;
  margin-top: 20px;

  &:hover {
    background-color: #1a91da;
  }
`;

const UserInfo = styled.div`
  margin-top: 20px;
  display: flex;
  align-items: center;
  padding: 10px;
  border-radius: 30px;
  cursor: pointer;

  &:hover {
    background-color: #e8f5fe;
  }
`;

const ProfileImage = styled.img`
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
`;

const UserDetails = styled.div`
  flex-grow: 1;
`;

const UserName = styled.p`
  font-weight: bold;
  margin: 0;
`;

const UserHandle = styled.p`
  color: #657786;
  margin: 0;
`;

const LogoutButton = styled.button`
  background: none;
  border: none;
  color: #1da1f2;
  cursor: pointer;
  font-weight: bold;
`;

const Sidebar: React.FC = () => {
  const { logout, user } = useAuth();

  return (
    <SidebarContainer>
      <Logo>
        <FaHome />
      </Logo>
      <Nav>
        <ul>
          <li>
            <a href="/">
              <FaHome /> Home
            </a>
          </li>
          <li>
            <a href="/explore">
              <FaHashtag /> Explore
            </a>
          </li>
          <li>
            <a href="/notifications">
              <FaBell /> Notifications
            </a>
          </li>
          <li>
            <a href="/messages">
              <FaEnvelope /> Messages
            </a>
          </li>
          <li>
            <a href="/bookmarks">
              <FaBookmark /> Bookmarks
            </a>
          </li>
          <li>
            <a href="/lists">
              <FaList /> Lists
            </a>
          </li>
          <li>
            <a href="/profile">
              <FaUser /> Profile
            </a>
          </li>
          <li>
            <a href="/more">
              <FaEllipsisH /> More
            </a>
          </li>
        </ul>
      </Nav>
      <ChirpButton>Chirp</ChirpButton>
      <UserInfo>
        <ProfileImage
          src={user?.profileImage || "https://via.placeholder.com/40"}
          alt="Profile"
        />
        <UserDetails>
          <UserName>{user?.name || "User Name"}</UserName>
          <UserHandle>@{user?.username || "username"}</UserHandle>
        </UserDetails>
        <LogoutButton onClick={logout}>Logout</LogoutButton>
      </UserInfo>
    </SidebarContainer>
  );
};

export default Sidebar;
