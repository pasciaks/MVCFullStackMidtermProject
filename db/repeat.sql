-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema repeatdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `repeatdb` ;

-- -----------------------------------------------------
-- Schema repeatdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `repeatdb` DEFAULT CHARACTER SET utf8 ;
USE `repeatdb` ;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `organization` ;

CREATE TABLE IF NOT EXISTS `organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(75) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NULL,
  `image_url` VARCHAR(2048) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 0,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` INT NOT NULL,
  `organization_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  INDEX `fk_user_organization1_idx` (`organization_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aircraft_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aircraft_type` ;

CREATE TABLE IF NOT EXISTS `aircraft_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aircraft_type` VARCHAR(75) NOT NULL,
  `image_url` VARCHAR(2048) NULL DEFAULT 'images/default.png',
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `aircraft_type_UNIQUE` (`aircraft_type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aircraft`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aircraft` ;

CREATE TABLE IF NOT EXISTS `aircraft` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tail_number` VARCHAR(16) NOT NULL,
  `aircraft_type_id` INT NOT NULL,
  `organization_id` INT NOT NULL,
  `active` TINYINT NULL DEFAULT 1,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tail_number_UNIQUE` (`tail_number` ASC),
  INDEX `fk_aircraft_aircraft_type1_idx` (`aircraft_type_id` ASC),
  INDEX `fk_aircraft_organization1_idx` (`organization_id` ASC),
  CONSTRAINT `fk_aircraft_aircraft_type1`
    FOREIGN KEY (`aircraft_type_id`)
    REFERENCES `aircraft_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aircraft_organization1`
    FOREIGN KEY (`organization_id`)
    REFERENCES `organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `experience_type_requirement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `experience_type_requirement` ;

CREATE TABLE IF NOT EXISTS `experience_type_requirement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `minutes_required` INT NOT NULL,
  `aircraft_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_experience_requirements_aircraft_type1_idx` (`aircraft_type_id` ASC),
  CONSTRAINT `fk_experience_requirements_aircraft_type1`
    FOREIGN KEY (`aircraft_type_id`)
    REFERENCES `aircraft_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `experience_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `experience_type` ;

CREATE TABLE IF NOT EXISTS `experience_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  `experience_type_requirement_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC),
  INDEX `fk_experience_type_experience_type_requirement1_idx` (`experience_type_requirement_id` ASC),
  CONSTRAINT `fk_experience_type_experience_type_requirement1`
    FOREIGN KEY (`experience_type_requirement_id`)
    REFERENCES `experience_type_requirement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pilot_log_entry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pilot_log_entry` ;

CREATE TABLE IF NOT EXISTS `pilot_log_entry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `stop_time` DATETIME NULL,
  `user_id` INT NOT NULL,
  `experience_type_id` INT NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_pilot_experience_user1_idx` (`user_id` ASC),
  INDEX `fk_pilot_log_entry_experience_type1_idx` (`experience_type_id` ASC),
  CONSTRAINT `fk_pilot_experience_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pilot_log_entry_experience_type1`
    FOREIGN KEY (`experience_type_id`)
    REFERENCES `experience_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `certification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `certification` ;

CREATE TABLE IF NOT EXISTS `certification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `required` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pilot_certification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pilot_certification` ;

CREATE TABLE IF NOT EXISTS `pilot_certification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `effective_date` DATE NOT NULL,
  `details` VARCHAR(255) NULL,
  `passed` TINYINT NULL,
  `certification_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `expiration_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pilot_certification_certification1_idx` (`certification_id` ASC),
  INDEX `fk_pilot_certification_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_pilot_certification_certification1`
    FOREIGN KEY (`certification_id`)
    REFERENCES `certification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pilot_certification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS repeatuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'repeatuser'@'localhost' IDENTIFIED BY 'repeatuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'repeatuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `role` (`id`, `name`) VALUES (DEFAULT, 'pilot');
INSERT INTO `role` (`id`, `name`) VALUES (DEFAULT, 'clerk');
INSERT INTO `role` (`id`, `name`) VALUES (DEFAULT, 'commander');
INSERT INTO `role` (`id`, `name`) VALUES (DEFAULT, 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `organization`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `organization` (`id`, `name`, `enabled`) VALUES (DEFAULT, '1-501AB', 1);
INSERT INTO `organization` (`id`, `name`, `enabled`) VALUES (DEFAULT, '1-110AB', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `user` (`id`, `username`, `password`, `date_of_birth`, `image_url`, `enabled`, `created_at`, `updated_at`, `role_id`, `organization_id`) VALUES (1, 'maverick', 'topgun', '1999-04-30', 'https://m.media-amazon.com/images/M/MV5BYWI1ZDQ4ZDItNjk0Ny00ZDcyLWI5MjctMmFkZjdkODI5ZGRlXkEyXkFqcGdeQWRvb2xpbmhk._V1_.jpg', 1, '2024-04-19 10:21:00', '2024-04-19 10:21:00', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aircraft_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `aircraft_type` (`id`, `aircraft_type`, `image_url`, `enabled`) VALUES (DEFAULT, 'ah64d', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aircraft`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `aircraft` (`id`, `tail_number`, `aircraft_type_id`, `organization_id`, `active`, `enabled`) VALUES (DEFAULT, 'T1679', 1, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `experience_type_requirement`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `experience_type_requirement` (`id`, `minutes_required`, `aircraft_type_id`) VALUES (DEFAULT, 60, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `experience_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `experience_type` (`id`, `description`, `enabled`, `experience_type_requirement_id`) VALUES (1, 'Day flight minutes', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pilot_log_entry`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `pilot_log_entry` (`id`, `start_time`, `stop_time`, `user_id`, `experience_type_id`, `created_at`) VALUES (1, '2024-04-19', NULL, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `certification`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `certification` (`id`, `description`, `required`) VALUES (DEFAULT, 'medical', 1);
INSERT INTO `certification` (`id`, `description`, `required`) VALUES (DEFAULT, 'clearance', 1);
INSERT INTO `certification` (`id`, `description`, `required`) VALUES (DEFAULT, 'gun table 6', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pilot_certification`
-- -----------------------------------------------------
START TRANSACTION;
USE `repeatdb`;
INSERT INTO `pilot_certification` (`id`, `effective_date`, `details`, `passed`, `certification_id`, `user_id`, `expiration_date`) VALUES (DEFAULT, '2023-01-01', 'flight physical short', 1, 1, 1, NULL);
INSERT INTO `pilot_certification` (`id`, `effective_date`, `details`, `passed`, `certification_id`, `user_id`, `expiration_date`) VALUES (DEFAULT, '2024-02-12', 'ts/sci', 1, 2, 1, NULL);

COMMIT;

