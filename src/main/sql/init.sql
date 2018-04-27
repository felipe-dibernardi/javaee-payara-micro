/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  felipe
 * Created: Apr 27, 2018
 */
CREATE SCHEMA condominium;

CREATE USER 'condominium'@'localhost' identified by 'c0nd0';

GRANT ALL ON condominium.* TO 'condominium'@'localhost';

CREATE TABLE condominium.user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    type VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE condominium.resident (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    block VARCHAR(8) NOT NULL,
    apartment INT(5) NOT NULL,
    document VARCHAR(32),
    phone VARCHAR(16),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE VIEW condominium.vi_user_type AS SELECT username, type FROM condominium.user;

INSERT INTO user (username, password, type) VALUES ('admin', MD5('admin'), 'ADMINISTRATOR');