-- Database Name:
CREATE DATABASE data_manager;

USE data_manager;

CREATE USER 'veris'@'localhost' IDENTIFIED BY 'qwerty';
GRANT ALL PRIVILEGES ON data_manager.* TO 'veris'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE users(
    user_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_admin VARCHAR(1) DEFAULT NULL,
    PRIMARY KEY(user_id)
);

CREATE TABLE books(
    book_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL UNIQUE,
    pages INT,
    litres_rating FLOAT,
    live_lib_rating FLOAT,
    PRIMARY KEY(book_id)
);

CREATE TABLE authors(
    author_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL UNIQUE,
    PRIMARY KEY(author_id)
);

CREATE TABLE genres(
    genre_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL UNIQUE,
    PRIMARY KEY(genre_id)
);

CREATE TABLE book_and_author(
    book_id INT NOT NULL,
    author_id INT NOT NULL
);

CREATE TABLE book_and_genre(
    book_id INT NOT NULL,
    genre_id INT NOT NULL
);

CREATE TABLE favourite_books(
    book_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE favourite_authors(
    author_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE favourite_genres(
    genre_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE meetings(
    meeting_id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    place VARCHAR(40) NOT NULL UNIQUE,
    datetime DATE NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY(meeting_id)
);

CREATE TABLE tasks(
    task_id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    task_name VARCHAR(60),
    task_text VARCHAR(200),
    deadline DATE,
    is_done VARCHAR(1),
    PRIMARY KEY(task_id)
);

CREATE TABLE incomes(
    income_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    date_get DATE NOT NULL,
    PRIMARY KEY(income_id)
);

CREATE TABLE expenses(
    expense_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    amount INT NOT NULL,
    date_get DATE NOT NULL,
    is_regular VARCHAR(1) NOT NULL,
    PRIMARY KEY(expense_id)
);

CREATE TABLE records(
    record_id INT NOT NULL AUTO_INCREMENT,
     meeting_id INT NOT NULL,
     user_id INT NOT NULL,
     is_passed BOOLEAN,
     rating FLOAT,
     price INT,
     sale INT,
     PRIMARY KEY (record_id)
);

CREATE TABLE rewards(
     reward_id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(200) NOT NULL,
     reason VARCHAR(300),
     promo VARCHAR(30) UNIQUE,
     image VARCHAR(30),
     tests_required INT,
     meetings_required INT,
     sale INT,
     PRIMARY KEY (reward_id)
);

CREATE TABLE user_and_reward(
    user_id INT NOT NULL,
    reward_id INT NOT NULL,
    is_used BOOLEAN
);

CREATE TABLE polls(
     poll_id INT NOT NULL AUTO_INCREMENT,
     question VARCHAR(200) NOT NULL,
     var1 VARCHAR(100) NOT NULL,
     var2 VARCHAR(100) NOT NULL,
     var3 VARCHAR(100) NOT NULL,
     var4 VARCHAR(100) NOT NULL,
     counter1 INT,
     counter2 INT,
     counter3 INT,
     counter4 INT,
     counterN1 INT,
     counterN2 INT,
     counterN3 INT,
     counterN4 INT,
     is_active BOOLEAN,
     PRIMARY KEY (poll_id)
);

CREATE TABLE user_and_poll(
    user_id INT NOT NULL,
    poll_id INT NOT NULL
);

CREATE TABLE user_and_test(
    user_id INT NOT NULL,
    test_id INT NOT NULL,
    estimation DOUBLE
);

CREATE TABLE test(
    test_id INT NOT NULL AUTO_INCREMENT,
    test_name VARCHAR(60),
    complexity VARCHAR(60)
);

CREATE TABLE question(
    question_id INT NOT NULL AUTO_INCREMENT,
    question_name VARCHAR(200) NOT NULL,
    var1 VARCHAR(60) NOT NULL,
    var2 VARCHAR(60) NOT NULL,
    var3 VARCHAR(60) NOT NULL,
    var4 VARCHAR(60) NOT NULL
)

CREATE TABLE test_and_question(
    test_id INT NOT NULL,
    question_id INT NOT NULL
);