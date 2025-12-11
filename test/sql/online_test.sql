-- Create database
CREATE DATABASE IF NOT EXISTS onlinetest;
USE onlinetest;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(10) NOT NULL
);

-- Sample Admin & Student
INSERT INTO users(username, password, role) VALUES
('admin', 'admin123', 'admin'),
('student', 'student123', 'student');

-- Questions table
CREATE TABLE IF NOT EXISTS questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(500) NOT NULL,
    optionA VARCHAR(100) NOT NULL,
    optionB VARCHAR(100) NOT NULL,
    optionC VARCHAR(100) NOT NULL,
    optionD VARCHAR(100) NOT NULL,
    answer VARCHAR(100) NOT NULL
);

-- Sample Questions
INSERT INTO questions(question, optionA, optionB, optionC, optionD, answer) VALUES
('What is Java?', 'A programming language', 'A coffee', 'An island', 'None', 'A programming language'),
('Which one is JavaFX?', 'A GUI framework', 'A database', 'A JVM tool', 'None', 'A GUI framework');

-- Results table
CREATE TABLE IF NOT EXISTS results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    score INT NOT NULL,
    FOREIGN KEY(userId) REFERENCES users(id)
);
