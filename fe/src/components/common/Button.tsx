import styled from "styled-components";

export const Button = styled.button`
  background-color: #00acee; // Twitter blue, you might want to choose a different color
  color: white;
  border: none;
  border-radius: 50px;
  padding: 12px;
  font-size: 15px;
  font-weight: bold;
  cursor: pointer;

  &:hover {
    background-color: #0096ce;
  }
`;
