package viet.io.chirpchirp.dto;


import lombok.Data;

import java.util.UUID;

@Data

public class UserDTO {

    private UUID id;
    private String username;
    private String displayName;
    private String bio;
    private String profilePictureUrl;
    private String coverPhotoUrl;
    private int followerCount;          // Number of followers
    private int followingCount;         // Number of accounts user is following
    private int tweetCount;             // Number of tweets by the user
    private boolean isVerified;
    private boolean isPrivate;

    // Constructor, Getters, and Setters
}
