DROP DATABASE IF EXISTS wishmaster;
CREATE SCHEMA wishmaster;
DROP TABLE IF EXISTS wishmaster.wishlist;
CREATE TABLE wishmaster.wishlist (
	`id` INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `password` VARCHAR(16) NOT NULL);
DROP TABLE IF EXISTS wishmaster.item;
CREATE TABLE wishmaster.item (
	`id` INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(40) NOT NULL,
    `link` VARCHAR(255) NULL,
    `wishlist_id` INT NOT NULL,
    FOREIGN KEY (`wishlist_id`) REFERENCES wishlist(id));
    
