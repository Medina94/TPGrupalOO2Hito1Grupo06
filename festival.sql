-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`festival`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`festival` (
  `id_festival` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `temporada` VARCHAR(50) NOT NULL,
  `fecha_Inicio` DATE NOT NULL,
  `fecha_fin` DATE NOT NULL,
  PRIMARY KEY (`id_festival`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`empleado` (
  `id_empleado` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `dni` INT NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `fecha_ingreso` DATE NOT NULL,
  `sueldo_base` DOUBLE NOT NULL,
  `id_unidad_venta` BIGINT NULL,
  `antiguedad` INT NULL,
  PRIMARY KEY (`id_empleado`),
  INDEX `fk_empleado_unidadVenta1_idx` (`id_unidad_venta` ASC) VISIBLE,
  CONSTRAINT `fk_empleado_unidadVenta1`
    FOREIGN KEY (`id_unidad_venta`)
    REFERENCES `mydb`.`unidad_venta` (`id_unidad_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`unidad_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`unidad_venta` (
  `id_unidad_venta` BIGINT NOT NULL AUTO_INCREMENT,
  `id_responsable` BIGINT NOT NULL,
  `id_festival` BIGINT NOT NULL,
  `nombre_Comercial` VARCHAR(50) NOT NULL,
  `superficie` DOUBLE NOT NULL,
  `codigo` VARCHAR(50) NOT NULL,
  `costos_superficie` DOUBLE NOT NULL,
  PRIMARY KEY (`id_unidad_venta`),
  INDEX `fk_unidad_venta_festival1_idx` (`id_festival` ASC) VISIBLE,
  INDEX `fk_encargado1_idx` (`id_responsable` ASC) VISIBLE,
  CONSTRAINT `fk_unidad_venta_festival1`
    FOREIGN KEY (`id_festival`)
    REFERENCES `mydb`.`festival` (`id_festival`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_responsable1`
    FOREIGN KEY (`id_responsable`)
    REFERENCES `mydb`.`empleado` (`id_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pedido` (
  `id_pedido` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `id_festival` BIGINT NOT NULL,
  `id_unidadVenta` BIGINT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedido_unidadVenta1_idx` (`id_unidadVenta` ASC) VISIBLE,
  INDEX `fk_pedido_festival1_idx` (`id_festival` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_unidadVenta1`
    FOREIGN KEY (`id_unidadVenta`)
    REFERENCES `mydb`.`unidad_venta` (`id_unidad_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_festival1`
    FOREIGN KEY (`id_festival`)
    REFERENCES `mydb`.`festival` (`id_festival`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`plato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`plato` (
  `id_plato` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `precio_venta` DOUBLE NOT NULL,
  `costo_produccion` DOUBLE NOT NULL,
  `id_unidad_venta` BIGINT NOT NULL,
  PRIMARY KEY (`id_plato`),
  INDEX `fk_plato_unidadVenta1_idx` (`id_unidad_venta` ASC) VISIBLE,
  CONSTRAINT `fk_plato_unidadVenta1`
    FOREIGN KEY (`id_unidad_venta`)
    REFERENCES `mydb`.`unidad_venta` (`id_unidad_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`venta` (
  `id_venta` BIGINT NOT NULL AUTO_INCREMENT,
  `id_plato` BIGINT NOT NULL,
  `cantidad` INT NOT NULL,
  `id_pedido` BIGINT NOT NULL,
  PRIMARY KEY (`id_venta`),
  INDEX `fk_venta_pedido1_idx` (`id_pedido` ASC) VISIBLE,
  INDEX `fk_venta_plato1_idx` (`id_plato` ASC) VISIBLE,
  CONSTRAINT `fk_venta_pedido1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `mydb`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_plato1`
    FOREIGN KEY (`id_plato`)
    REFERENCES `mydb`.`plato` (`id_plato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`food_truck`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`food_truck` (
  `id_unidad_venta` BIGINT NOT NULL,
  `patente` VARCHAR(50) NOT NULL,
  `conexion_electrica` TINYINT NOT NULL,
  `plus_por_electricidad` DOUBLE NOT NULL,
  INDEX `fk_foodTruck_unidadVenta1_idx` (`id_unidad_venta` ASC) VISIBLE,
  PRIMARY KEY (`id_unidad_venta`),
  CONSTRAINT `fk_foodTruck_unidadVenta1`
    FOREIGN KEY (`id_unidad_venta`)
    REFERENCES `mydb`.`unidad_venta` (`id_unidad_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`puesto_desarmable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`puesto_desarmable` (
  `id_unidad_venta` BIGINT NOT NULL,
  `cantidad_carpas` INT NOT NULL,
  `minutos` INT NOT NULL,
  `costos_por_montaje` DOUBLE NOT NULL,
  INDEX `fk_puestoDesarmable_unidadVenta1_idx` (`id_unidad_venta` ASC) VISIBLE,
  PRIMARY KEY (`id_unidad_venta`),
  CONSTRAINT `fk_puestoDesarmable_unidadVenta1`
    FOREIGN KEY (`id_unidad_venta`)
    REFERENCES `mydb`.`unidad_venta` (`id_unidad_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cocinero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cocinero` (
  `id_empleado` BIGINT NOT NULL,
  `especialidad_culinaria` VARCHAR(50) NOT NULL,
  `plus` DOUBLE NOT NULL,
  INDEX `fk_cocinero_empleado1_idx` (`id_empleado` ASC) VISIBLE,
  PRIMARY KEY (`id_empleado`),
  CONSTRAINT `fk_cocinero_empleado1`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `mydb`.`empleado` (`id_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cajero` (
  `id_empleado` BIGINT NOT NULL,
  `turno` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  INDEX `fk_cajero_empleado1_idx` (`id_empleado` ASC) VISIBLE,
  CONSTRAINT `fk_cajero_empleado1`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `mydb`.`empleado` (`id_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
