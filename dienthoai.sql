-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dienthoai
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dienthoai
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dienthoai` DEFAULT CHARACTER SET utf8 ;
USE `dienthoai` ;

-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `avatar` VARCHAR(255) NULL,
  `name` VARCHAR(100) NULL,
  `gender` VARCHAR(5) NULL,
  `address` VARCHAR(255) NULL,
  `password_hash` VARCHAR(255) NULL,
  `phone_number` VARCHAR(15) NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(105) NULL,
  `short_desc` VARCHAR(255) NULL,
  `create_date` DATE NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `short_desc` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `product_main_image_id` INT NULL,
  `manufacturer` VARCHAR(100) NULL,
  `model` VARCHAR(100) NULL,
  `screen` VARCHAR(100) NULL,
  `resolution` VARCHAR(100) NULL,
  `cpu` VARCHAR(100) NULL,
  `ram` VARCHAR(100) NULL,
  `camera` VARCHAR(100) NULL,
  `pin` VARCHAR(100) NULL,
  `other` VARCHAR(255) NULL,
  `create_date` VARCHAR(45) NULL,
  `year_guaratee` INT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_tbl_product_tbl_category_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_tbl_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `dienthoai`.`tbl_category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_image` (
  `product_image_id` INT NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(255) NULL,
  `create_date` DATE NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`product_image_id`),
  INDEX `fk_tbl_product_image_tbl_detail_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_image_tbl_detail_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_cart` (
  `cart_id` INT UNSIGNED NOT NULL,
  `guid` VARCHAR(100) NULL,
  `username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cart_id`),
  INDEX `fk_tbl_cart_tbl_user1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_cart_tbl_user1`
    FOREIGN KEY (`username`)
    REFERENCES `dienthoai`.`tbl_user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_cart` (
  `product_cart_id` INT NOT NULL,
  `amount` INT NULL,
  `size` VARCHAR(50) NULL,
  `color` VARCHAR(50) NULL,
  `price` DOUBLE NULL,
  `cart_id` INT UNSIGNED NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`product_cart_id`),
  INDEX `fk_tbl_product_cart_tbl_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_tbl_product_cart_tbl_cart1_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_cart_tbl_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_cart_tbl_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `dienthoai`.`tbl_cart` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `guid` VARCHAR(100) NULL,
  `customer_name` VARCHAR(100) NULL,
  `phone_number` VARCHAR(15) NULL,
  `address` VARCHAR(255) NULL,
  `create_date` DATE NULL,
  `username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_tbl_order_tbl_user1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_order_tbl_user1`
    FOREIGN KEY (`username`)
    REFERENCES `dienthoai`.`tbl_user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_order` (
  `product_order_id` INT NOT NULL AUTO_INCREMENT,
  `amount` INT NULL,
  `price` DOUBLE NULL,
  `status` INT NULL,
  `date_payment` DATE NULL,
  `year_guaratee` INT NULL,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `size` VARCHAR(50) NULL,
  `color` VARCHAR(50) NULL,
  PRIMARY KEY (`product_order_id`),
  INDEX `fk_tbl_product_order_tbl_order1_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_tbl_product_order_tbl_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_order_tbl_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `dienthoai`.`tbl_order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_order_tbl_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`),
  INDEX `fk_tbl_user_role_tbl_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_tbl_user_role_tbl_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_user_role_tbl_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dienthoai`.`tbl_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_user_role_tbl_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `dienthoai`.`tbl_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `guid` VARCHAR(100) NULL,
  `customer_name` VARCHAR(100) NULL,
  `phone_number` VARCHAR(15) NULL,
  `address` VARCHAR(255) NULL,
  `create_date` DATE NULL,
  `username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_tbl_order_tbl_user1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_order_tbl_user1`
    FOREIGN KEY (`username`)
    REFERENCES `dienthoai`.`tbl_user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_promotion` (
  `promotion_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`promotion_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_promotion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `discount` INT NULL,
  `promotion_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_product_promotion_tbl_promotion2_idx` (`promotion_id` ASC) VISIBLE,
  INDEX `fk_tbl_product_promotion_tbl_product2_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_promotion_tbl_promotion2`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `dienthoai`.`tbl_promotion` (`promotion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_promotion_tbl_product2`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`table1` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_rate` (
  `rate_id` INT NOT NULL AUTO_INCREMENT,
  `star` INT NULL,
  `username` VARCHAR(100) NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`rate_id`),
  INDEX `fk_tbl_rate_tbl_user1_idx` (`username` ASC) VISIBLE,
  INDEX `fk_tbl_rate_tbl_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_rate_tbl_user1`
    FOREIGN KEY (`username`)
    REFERENCES `dienthoai`.`tbl_user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_rate_tbl_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`table2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`table2` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_size_color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_size_color` (
  `size_color_id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `size` VARCHAR(50) NULL,
  `color` VARCHAR(50) NULL,
  `amount` INT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`size_color_id`),
  INDEX `fk_tbl_product_tbl_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_tbl_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_promotion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `discount` INT NULL,
  `promotion_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_product_promotion_tbl_promotion2_idx` (`promotion_id` ASC) VISIBLE,
  INDEX `fk_tbl_product_promotion_tbl_product2_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_promotion_tbl_promotion2`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `dienthoai`.`tbl_promotion` (`promotion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_product_promotion_tbl_product2`
    FOREIGN KEY (`product_id`)
    REFERENCES `dienthoai`.`tbl_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dienthoai`.`tbl_product_guarantee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dienthoai`.`tbl_product_guarantee` (
  `id` INT NOT NULL,
  `detail` VARCHAR(255) NULL,
  `day_guarantee` DATE NULL,
  `product_order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tbl_product_guarantee_tbl_product_order1_idx` (`product_order_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_product_guarantee_tbl_product_order1`
    FOREIGN KEY (`product_order_id`)
    REFERENCES `dienthoai`.`tbl_product_order` (`product_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
