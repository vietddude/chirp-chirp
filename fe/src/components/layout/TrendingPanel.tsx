import React from "react";
import styled from "styled-components";

const PanelContainer = styled.aside`
  width: 300px;
  padding: 20px;
  background-color: #f7f9fa;
  border-left: 1px solid #e1e8ed;
  overflow-y: auto;
`;

const PanelTitle = styled.h2`
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 15px;
`;

const TrendingList = styled.ul`
  list-style-type: none;
  padding: 0;
`;

const TrendingItem = styled.li`
  margin-bottom: 15px;
`;

const HashTag = styled.p`
  font-weight: bold;
  margin: 0;
`;

const TweetCount = styled.p`
  color: #657786;
  font-size: 13px;
  margin: 0;
`;

const TrendingPanel: React.FC = () => {
  // This would typically come from an API
  const trendingTopics = [
    { tag: "#ChirpChirp", tweets: "255K" },
    { tag: "#CodeLife", tweets: "100K" },
    { tag: "#AIRevolution", tweets: "50K" },
    { tag: "#WebDev", tweets: "30K" },
    { tag: "#FridayFeeling", tweets: "20K" },
  ];

  return (
    <PanelContainer>
      <PanelTitle>Trends for you</PanelTitle>
      <TrendingList>
        {trendingTopics.map((topic, index) => (
          <TrendingItem key={index}>
            <HashTag>{topic.tag}</HashTag>
            <TweetCount>{topic.tweets} Chirps</TweetCount>
          </TrendingItem>
        ))}
      </TrendingList>
    </PanelContainer>
  );
};

export default TrendingPanel;
