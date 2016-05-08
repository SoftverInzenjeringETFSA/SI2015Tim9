-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema vrtic
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `vrtic` ;

-- -----------------------------------------------------
-- Schema vrtic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vrtic` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `vrtic` ;

-- -----------------------------------------------------
-- Table `vrtic`.`Grupe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Grupe` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Grupe` (
  `idGrupe` INT NOT NULL COMMENT '',
  `naziv` VARCHAR(45) NULL COMMENT '',
  `RedniBroj` INT(11) NULL COMMENT '',
  `Kapacitet` INT(11) NULL COMMENT '',
  PRIMARY KEY (`idGrupe`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Djeca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Djeca` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Djeca` (
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
    REFERENCES `vrtic`.`Grupe` (`idGrupe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Korisnici`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Korisnici` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Korisnici` (
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
-- Table `vrtic`.`Uplate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Uplate` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Uplate` (
  `idUplate` INT NOT NULL COMMENT '',
  `DatumUplate` DATE NULL COMMENT '',
  `VisinaUplate` DOUBLE NULL COMMENT '',
  `idDjeca` INT NULL COMMENT '',
  `zaMjesec` INT NULL COMMENT '',
  `zaGodinu` INT NULL COMMENT '',
  PRIMARY KEY (`idUplate`)  COMMENT '',
  INDEX `idDjeca_idx` (`idDjeca` ASC)  COMMENT '',
  CONSTRAINT `idDjeca`
    FOREIGN KEY (`idDjeca`)
    REFERENCES `vrtic`.`Djeca` (`idDjeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Vaspitaci`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Vaspitaci` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Vaspitaci` (
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
    REFERENCES `vrtic`.`Grupe` (`idGrupe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Aktivnosti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Aktivnosti` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Aktivnosti` (
  `idAktivnosti` INT NOT NULL COMMENT '',
  `Naziv` VARCHAR(255) NULL COMMENT '',
  `BrojDjece` INT NULL COMMENT '',
  `cijena` INT NULL COMMENT '',
  PRIMARY KEY (`idAktivnosti`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`AktivnostiDjeca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`AktivnostiDjeca` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`AktivnostiDjeca` (
  `idDjeca` INT NOT NULL COMMENT '',
  `idAktivnosti` INT NOT NULL COMMENT '',
  INDEX `fk_Aktivnosti_idx` (`idAktivnosti` ASC)  COMMENT '',
  INDEX `fk_Djeca_idx` (`idDjeca` ASC)  COMMENT '',
  CONSTRAINT `fk_Djeca`
    FOREIGN KEY (`idDjeca`)
    REFERENCES `vrtic`.`Djeca` (`idDjeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aktivnosti`
    FOREIGN KEY (`idAktivnosti`)
    REFERENCES `vrtic`.`Aktivnosti` (`idAktivnosti`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Zaduzenja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Zaduzenja` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Zaduzenja` (
  `idZaduzenja` INT NOT NULL COMMENT '',
  `idDjeca` INT NULL COMMENT '',
  `mjesec` VARCHAR(45) NULL COMMENT '',
  `godina` INT NULL COMMENT '',
  PRIMARY KEY (`idZaduzenja`)  COMMENT '',
  INDEX `fk_djeca_1_idx` (`idDjeca` ASC)  COMMENT '',
  CONSTRAINT `fk_djeca_1`
    FOREIGN KEY (`idDjeca`)
    REFERENCES `vrtic`.`Djeca` (`idDjeca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vrtic`.`Termini`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vrtic`.`Termini` ;

CREATE TABLE IF NOT EXISTS `vrtic`.`Termini` (
  `idTermini` INT NOT NULL COMMENT '',
  `idAktivnosti` INT NULL COMMENT '',
  `idGrupe` INT NULL COMMENT '',
  `vrijemePocetka` VARCHAR(45) NULL COMMENT '',
  `vrijemeZavrsetka` VARCHAR(45) NULL COMMENT '',
  `dan` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`idTermini`)  COMMENT '',
  INDEX `fk_aktivnosti_1_idx` (`idAktivnosti` ASC)  COMMENT '',
  INDEX `fk_grupe_1_idx` (`idGrupe` ASC)  COMMENT '',
  CONSTRAINT `fk_aktivnosti_1`
    FOREIGN KEY (`idAktivnosti`)
    REFERENCES `vrtic`.`Aktivnosti` (`idAktivnosti`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupe_1`
    FOREIGN KEY (`idGrupe`)
    REFERENCES `vrtic`.`Grupe` (`idGrupe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

