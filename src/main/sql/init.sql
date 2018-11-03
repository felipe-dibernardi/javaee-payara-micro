/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  felipe
 * Created: Apr 27, 2018
 */
DROP SCHEMA IF EXISTS condominium;
CREATE SCHEMA condominium;

DROP SCHEMA IF EXISTS condominium_test;
CREATE SCHEMA condominium_test;

DROP USER IF EXISTS 'condominium'@'%';
CREATE USER 'condominium'@'%' identified by 'c0nd0';

GRANT ALL ON condominium.* TO 'condominium'@'%';

GRANT ALL ON condominium_test.* TO 'condominium'@'%';

DROP TABLE IF EXISTS condominium.user;
CREATE TABLE condominium.user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    type VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS condominium_test.user;
CREATE TABLE condominium_test.user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    type VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS condominium.resident;
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

DROP TABLE IF EXISTS condominium_test.resident;
CREATE TABLE condominium_test.resident (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    block VARCHAR(8) NOT NULL,
    apartment INT(5) NOT NULL,
    document VARCHAR(32),
    phone VARCHAR(16),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

DROP VIEW IF EXISTS condominium.vi_user_type;
CREATE VIEW condominium.vi_user_type AS SELECT username, type FROM condominium.user;

INSERT INTO condominium.user (username, password, type) VALUES ('admin', MD5('admin'), 'ADMINISTRATOR');