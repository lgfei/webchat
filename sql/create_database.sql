CREATE DATABASE IF NOT EXISTS webchat;
CREATE USER 'username'@'%' IDENTIFIED BY 'password';
GRANT ALL ON webchat.* TO 'username'@'%';