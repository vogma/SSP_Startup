-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema liga
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema liga
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `liga` DEFAULT CHARACTER SET utf8 ;
USE `liga` ;

-- -----------------------------------------------------
-- Table `liga`.`stadion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liga`.`stadion` ;

CREATE TABLE IF NOT EXISTS `liga`.`stadion` (
  `stadion_id` INT(11) NOT NULL AUTO_INCREMENT,
  `s_name` VARCHAR(200) NULL,
  `s_gps` VARCHAR(200) NULL,
  `s_adress` VARCHAR(200) NULL,
  `s_bildURL` VARCHAR(255) NULL,
  `s_kapazitaet` INT(11) NULL,
  PRIMARY KEY (`stadion_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liga`.`verein`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liga`.`verein` ;

CREATE TABLE IF NOT EXISTS `liga`.`verein` (
  `verein_id` INT(11) NOT NULL,
  `v_name` VARCHAR(200) NULL,
  `v_gruendung` DATE NULL,
  `v_trainer` VARCHAR(200) NULL,
  `v_praesident` VARCHAR(200) NULL,
  `v_ort` VARCHAR(200) NULL,
  `v_logoURL` VARCHAR(255) NULL,
  `v_stadionID` VARCHAR(45) NULL,
  `stadion_stadion_id` INT(11) NOT NULL,
  PRIMARY KEY (`verein_id`),
  INDEX `fk_verein_stadion1_idx` (`stadion_stadion_id` ASC),
  CONSTRAINT `fk_verein_stadion1`
    FOREIGN KEY (`stadion_stadion_id`)
    REFERENCES `liga`.`stadion` (`stadion_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liga`.`match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liga`.`match` ;

CREATE TABLE IF NOT EXISTS `liga`.`match` (
  `match_id` INT NOT NULL,
  `m_saison` INT NULL,
  `m_datum` DATETIME NULL,
  `m_zuschauer` INT(11) NULL,
  `m_schiri` VARCHAR(45) NULL,
  `m_stadion` VARCHAR(45) NULL,
  `m_stadionID` VARCHAR(45) NULL,
  `m_endergebnis` VARCHAR(45) NULL,
  `m_halbzeitergebnis` VARCHAR(45) NULL,
  `m_punkteHeim` INT NULL,
  `m_punkteGast` INT NULL,
  `m_heimID` INT NULL,
  `m_gastID` INT NULL,
  `verein_verein_id` INT(11) NOT NULL,
  PRIMARY KEY (`match_id`),
  INDEX `fk_match_verein_idx` (`verein_verein_id` ASC),
  CONSTRAINT `fk_match_verein`
    FOREIGN KEY (`verein_verein_id`)
    REFERENCES `liga`.`verein` (`verein_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liga`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liga`.`player` ;

CREATE TABLE IF NOT EXISTS `liga`.`player` (
  `player_id` INT(11) NOT NULL AUTO_INCREMENT,
  `p_name` VARCHAR(200) NULL,
  `p_position` VARCHAR(200) NULL,
  `p_geburtsdatum` DATE NULL,
  `p_nation` VARCHAR(200) NULL,
  `p_bildURL` VARCHAR(255) NULL,
  `p_vereinID` INT(11) NULL,
  `p_verein_id` INT(11) NOT NULL,
  PRIMARY KEY (`player_id`),
  INDEX `fk_player_verein1_idx` (`p_verein_id` ASC),
  CONSTRAINT `fk_player_verein1`
    FOREIGN KEY (`p_verein_id`)
    REFERENCES `liga`.`verein` (`verein_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liga`.`goal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liga`.`goal` ;

CREATE TABLE IF NOT EXISTS `liga`.`goal` (
  `goal_id` INT(11) NOT NULL,
  `g_toreHeim` INT(11) NULL,
  `g_toreGast` INT(11) NULL,
  `g_minute` INT(11) NULL,
  `g_schuetze` VARCHAR(200) NULL,
  `g_playerID` INT(11) NULL,
  `g_matchID` INT(11) NULL,
  `match_match_id` INT NOT NULL,
  `player_player_id` INT(11) NOT NULL,
  PRIMARY KEY (`goal_id`),
  INDEX `fk_goal_match1_idx` (`match_match_id` ASC),
  INDEX `fk_goal_player1_idx` (`player_player_id` ASC),
  CONSTRAINT `fk_goal_match1`
    FOREIGN KEY (`match_match_id`)
    REFERENCES `liga`.`match` (`match_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_goal_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `liga`.`player` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
