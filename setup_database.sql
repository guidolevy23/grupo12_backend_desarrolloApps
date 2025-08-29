-- Database setup script for Gimnasio Spring Boot application
-- Run this in MySQL Workbench after connecting as root

-- Create the database
CREATE DATABASE IF NOT EXISTS gimnasio_db;

-- Create user with privileges
CREATE USER IF NOT EXISTS 'myuser'@'localhost' IDENTIFIED BY 'secret';

-- Grant privileges
GRANT ALL PRIVILEGES ON gimnasio_db.* TO 'myuser'@'localhost';

-- Grant additional privileges for development
GRANT CREATE, DROP, ALTER, INDEX, REFERENCES ON gimnasio_db.* TO 'myuser'@'localhost';

-- Apply changes
FLUSH PRIVILEGES;

-- Verify the setup
SHOW DATABASES;
SELECT User, Host FROM mysql.user WHERE User = 'myuser';
SHOW GRANTS FOR 'myuser'@'localhost'; 