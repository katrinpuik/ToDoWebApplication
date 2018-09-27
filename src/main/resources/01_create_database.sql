CREATE DATABASE todos;

CREATE USER 'todouser'@'localhost' IDENTIFIED BY 'todopass';

GRANT ALL ON todos.* TO 'todouser'@'localhost';