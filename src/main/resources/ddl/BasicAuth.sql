
CREATE DATABASE IF NOT EXISTS basic_auth_db;

USE basic_auth_db;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

--CREATE TABLE IF NOT EXISTS roles (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL UNIQUE
--);
--
--CREATE TABLE IF NOT EXISTS user_roles (
--    user_id BIGINT NOT NULL,
--    role_id BIGINT NOT NULL,
--    PRIMARY KEY (user_id, role_id),
--    FOREIGN KEY (user_id) REFERENCES users(id),
--    FOREIGN KEY (role_id) REFERENCES roles(id)
--);

