CREATE TABLE follows
(
    id          UUID                        NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    follower_id UUID                        NOT NULL,
    followee_id UUID                        NOT NULL,
    CONSTRAINT pk_follows PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                  UUID                        NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    username            VARCHAR(255)                NOT NULL,
    email               VARCHAR(255)                NOT NULL,
    password            VARCHAR(255)                NOT NULL,
    display_name        VARCHAR(255)                NOT NULL,
    profile_picture_url VARCHAR(255)                NOT NULL,
    cover_photo_url     VARCHAR(255)                NOT NULL,
    is_verified         BOOLEAN DEFAULT FALSE       NOT NULL,
    is_private          BOOLEAN DEFAULT FALSE       NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FOLLOWEE FOREIGN KEY (followee_id) REFERENCES users (id);

ALTER TABLE follows
    ADD CONSTRAINT FK_FOLLOWS_ON_FOLLOWER FOREIGN KEY (follower_id) REFERENCES users (id);