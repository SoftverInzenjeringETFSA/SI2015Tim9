-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Grupe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Grupe` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Grupe` (
  `idGrupe` INT NOT NULL COMMENT '',
  `naziv` VARCHAR(45) NULL COMMENT '',
  `RedniBroj` INT(11) NULL COMMENT '',
  `Kapacitet` INT(11) NULL COMMENT '',
  PRIMARY KEY (`idGrupe`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Djeca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Djeca` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Djeca` (
  `idDjeca` INT NOT NULL COMMENT '',
  `Ime` VARCHAR(255) NULL COMMENT '',
  `Prezime` VARCHAR(255) NULL COMMENT '',
  `DatumRodjenja` VARCHAR(255) NULL COMMENT '',
  `AdresaPrebivalista` VARCHAR(255) NULL COMMENT '',
  `ImeRoditelja` VARCHAR(255) NULL COMMENT '',
  `BrojTelefona` VARCHAR(255) NULL COMMENT '',
  `PrezimeRoditelja` VARCHAR(255) NULL COMMENT '',
  `DatumUpisa` DATE NULL COMMENT '',
  `DatumIsteka` DATE NULL COMMENT '',
  `idGrupe` INT NULL COMMENT '',
  PRIMARY KEY (`idDjeca`)  COMMENT '',
  INDEX `fk_grupe_idx` (`idGrupe` ASC)  COMMENT '',
  CONSTRAINT `fk_grupe`
    FOREIGN KEY (`idGrupe`)
    REFERENCES `mydb`.`Grupe` (`idGrupe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Korisnici`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Korisnici` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Korisnici` (
  `idKorisnika` INT NOT NULL COMMENT '',
  `Ime` VARCHAR(255) NULL COMMENT '',
  `Prezime` VARCHAR(255) NULL COMMENT '',
  `KorisnickoIme` VARCHAR(255) NULL COMMENT '',
  `Sifra` VARCHAR(255) NULL COMMENT '',
  `Privilegije` VARCHAR(255) NULL COMMENT '',
  `BrojTelefona` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`idKorisnika`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Uplate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Uplate` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Uplate` (
  `idUplate` INT NOT NULL COMMENT '',
  `DatumUplate` DATE NULL COMMENT '',
  `VisinaUplate` DOUBLE NULL COMMENT '',
  `IzvrsenaUplata` VARCHAR(45) NULL COMMENT '',
  `idDjeca` INT NULL COMMENT '',
  PRIMARY KEY (`idUplate`)  COMMENT '',
  INDEX `idDjeca_idx` (`idDjeca` ASC)  COMMENT '',
  CONSTRAINT `idDjeca`
    FOREIGN KEY (`idDjeca`)
    REFERENCES `mydb`.`Djeca` (`idDjeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Vaspitaci`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Vaspitaci` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Vaspitaci` (
  `idVaspitaci` INT NOT NULL COMMENT '',
  `Ime` VARCHAR(255) NULL COMMENT '',
  `Prezime` VARCHAR(255) NULL COMMENT '',
  `BrojTelefona` VARCHAR(255) NULL COMMENT '',
  `AdresaPrebivalista` VARCHAR(255) NULL COMMENT '',
  `idGrupe` INT NULL COMMENT '',
  PRIMARY KEY (`idVaspitaci`)  COMMENT '',
  INDEX `GrupeID_idx` (`idGrupe` ASC)  COMMENT '',
  CONSTRAINT `GrupeID`
    FOREIGN KEY (`idGrupe`)
    REFERENCES `mydb`.`Grupe` (`idGrupe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Aktivnosti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Aktivnosti` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Aktivnosti` (
  `idAktivnosti` INT NOT NULL COMMENT '',
  `Naziv` VARCHAR(255) NULL COMMENT '',
  `BrojDjece` INT(11) NULL COMMENT '',
  `Aktivnosticol` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`idAktivnosti`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AktivnostiDjeca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AktivnostiDjeca` ;

CREATE TABLE IF NOT EXISTS `mydb`.`AktivnostiDjeca` (
  `idDjeca` INT NOT NULL COMMENT '',
  `idAktivnosti` INT NOT NULL COMMENT '',
  INDEX `fk_Aktivnosti_idx` (`idAktivnosti` ASC)  COMMENT '',
  INDEX `fk_Djeca_idx` (`idDjeca` ASC)  COMMENT '',
  CONSTRAINT `fk_Djeca`
    FOREIGN KEY (`idDjeca`)
    REFERENCES `mydb`.`Djeca` (`idDjeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aktivnosti`
    FOREIGN KEY (`idAktivnosti`)
    REFERENCES `mydb`.`Aktivnosti` (`idAktivnosti`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
