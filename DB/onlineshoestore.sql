-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OnlineShoeStore
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OnlineShoeStore` ;

-- -----------------------------------------------------
-- Schema OnlineShoeStore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OnlineShoeStore` DEFAULT CHARACTER SET utf8 ;
USE `OnlineShoeStore` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(50) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kind`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kind` ;

CREATE TABLE IF NOT EXISTS `kind` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brand`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brand` ;

CREATE TABLE IF NOT EXISTS `brand` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shoe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shoe` ;

CREATE TABLE IF NOT EXISTS `shoe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(5,2) NOT NULL,
  `type_id` INT NULL,
  `brand_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shoe_type1_idx` (`type_id` ASC) VISIBLE,
  INDEX `fk_shoe_brand1_idx` (`brand_id` ASC) VISIBLE,
  CONSTRAINT `fk_shoe_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `kind` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shoe_brand1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone` VARCHAR(100) NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item` ;

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(100) NULL,
  `size` DECIMAL(3,1) NULL,
  `shoe_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_item_shoe1_idx` (`shoe_id` ASC) VISIBLE,
  CONSTRAINT `fk_inventory_item_shoe1`
    FOREIGN KEY (`shoe_id`)
    REFERENCES `shoe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

CREATE TABLE IF NOT EXISTS `order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `confirmation_number` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart` ;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cart_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_cart_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cart_has_inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart_has_inventory_item` ;

CREATE TABLE IF NOT EXISTS `cart_has_inventory_item` (
  `cart_id` INT NOT NULL,
  `inventory_item_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`, `inventory_item_id`),
  INDEX `fk_cart_has_inventory_item_inventory_item1_idx` (`inventory_item_id` ASC) VISIBLE,
  INDEX `fk_cart_has_inventory_item_cart1_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_has_inventory_item_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_has_inventory_item_inventory_item1`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS student@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'student'@'localhost' IDENTIFIED BY 'student';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'student'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (1, 'username', 'password', true, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (2, 'name', 'pass', true, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `kind`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `kind` (`id`, `name`, `description`) VALUES (1, 'Running', NULL);
INSERT INTO `kind` (`id`, `name`, `description`) VALUES (2, 'Hiking', NULL);
INSERT INTO `kind` (`id`, `name`, `description`) VALUES (3, 'Dress', NULL);
INSERT INTO `kind` (`id`, `name`, `description`) VALUES (4, 'Work', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `brand`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `brand` (`id`, `name`) VALUES (1, 'Nike');
INSERT INTO `brand` (`id`, `name`) VALUES (2, 'Oxfords');
INSERT INTO `brand` (`id`, `name`) VALUES (3, 'Merrel');
INSERT INTO `brand` (`id`, `name`) VALUES (4, 'Caterpillar');

COMMIT;


-- -----------------------------------------------------
-- Data for table `shoe`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `shoe` (`id`, `price`, `type_id`, `brand_id`) VALUES (1, 30.99, 1, 2);
INSERT INTO `shoe` (`id`, `price`, `type_id`, `brand_id`) VALUES (2, 25.99, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `user_id`) VALUES (1, 'John', 'Doe', 'john.doe@gmail.com', '123-456-7890', 1);
INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `user_id`) VALUES (2, 'Jane', 'Dane', 'jane.dane@gmail.com', '123-456-7891', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `inventory_item` (`id`, `color`, `size`, `shoe_id`) VALUES (1, 'Green', 7.5, 1);
INSERT INTO `inventory_item` (`id`, `color`, `size`, `shoe_id`) VALUES (2, 'Pink', 8.5, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `order` (`id`, `date`, `confirmation_number`) VALUES (1, '2024-12-05 22:04:30', 'CONF123456');
INSERT INTO `order` (`id`, `date`, `confirmation_number`) VALUES (2, '2024-12-06 22:04:30', 'CONF123457');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cart`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `cart` (`id`, `customer_id`, `order_id`) VALUES (1, 1, 1);
INSERT INTO `cart` (`id`, `customer_id`, `order_id`) VALUES (2, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cart_has_inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `OnlineShoeStore`;
INSERT INTO `cart_has_inventory_item` (`cart_id`, `inventory_item_id`) VALUES (1, 1);
INSERT INTO `cart_has_inventory_item` (`cart_id`, `inventory_item_id`) VALUES (2, 2);

COMMIT;

