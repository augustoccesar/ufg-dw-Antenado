SET @@session.time_zone='+00:00';

CREATE DATABASE IF NOT EXISTS antenado;
USE antenado;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS markers;

CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(200),
  username VARCHAR(30) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(250),
  gender CHAR(1),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  PRIMARY KEY (id)
);

CREATE TABLE markers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT,
  title VARCHAR(50) NOT NULL,
  message VARCHAR(250) NOT NULL,
  priority TINYINT(2) NOT NULL DEFAULT 1,
  latitude DECIMAL(11, 8),
  longitude DECIMAL(11, 8),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);