-- Database Name:
CREATE DATABASE data_manager;

USE data_manager;

CREATE USER 'veris'@'localhost' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON data_manager.* TO 'veris'@'localhost';
FLUSH PRIVILEGES;

-- User table structure;
CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_admin VARCHAR(1) DEFAULT NULL,
    PRIMARY KEY(user_id)
);