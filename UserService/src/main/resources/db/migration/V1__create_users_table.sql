CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       username VARCHAR(64) NOT NULL,
                       email VARCHAR(64) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_time TIMESTAMP
);
