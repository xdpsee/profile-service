CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  gmt_create TIMESTAMP NOT NULL,
  gmt_modified TIMESTAMP NOT NULL,
  nickname VARCHAR(64) NOT NULL UNIQUE ,
  avatar VARCHAR(255),
  authorities TEXT NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE user_auth_infos (
  id BIGINT NOT NULL AUTO_INCREMENT,
  gmt_create TIMESTAMP NOT NULL,
  gmt_modified TIMESTAMP NOT NULL,
  type int NOT NULL,
  identifier VARCHAR(64) NOT NULL ,
  credential VARCHAR(255) NOT NULL ,
  user_id BIGINT DEFAULT 0,
  verified TINYINT DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (type,identifier)
);


CREATE TABLE user_open_infos (
  id BIGINT NOT NULL AUTO_INCREMENT,
  gmt_create TIMESTAMP NOT NULL,
  gmt_modified TIMESTAMP NOT NULL,
  type int NOT NULL,
  identifier VARCHAR(64) NOT NULL ,
  open_id BIGINT NOT NULL ,
  nickname VARCHAR(64) NOT NULL ,
  avatar VARCHAR(255) NOT NULL ,
  PRIMARY KEY (id),
  UNIQUE (type,open_id)
);


