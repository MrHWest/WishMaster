DROP DATABASE IF EXISTS wishmaster;
CREATE SCHEMA wishmaster;
DROP TABLE IF EXISTS wishlist;
CREATE TABLE wishmaster.wishlist (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
    `password` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`));
DROP TABLE IF EXISTS item;
CREATE TABLE wishmaster.item (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(40) NOT NULL,
    `link` VARCHAR(255) NULL,
    `wishlistID` INT NOT NULL,
    PRIMARY KEY (`id`));
