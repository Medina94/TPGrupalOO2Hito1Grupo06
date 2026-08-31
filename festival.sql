<<<<<<< HEAD
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
=======
CREATE DATABASE IF NOT EXISTS festival_db;
USE festival_db;

-- 1. Tabla EMPLEADO (Clase Base)
CREATE TABLE empleado (
    idEmpleado BIGINT AUTO_INCREMENT,
    edad INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni INT UNIQUE NOT NULL,
    fechaNacimiento DATE NOT NULL, 
    fechaIngreso DATE NOT NULL,
    sueldoBase DOUBLE NOT NULL,
    PRIMARY KEY (idEmpleado)
) ENGINE=InnoDB;

-- Subclase COCINERO (Joined)
CREATE TABLE cocinero (
    idEmpleado BIGINT NOT NULL,
    especialidadCulinaria VARCHAR(100) NOT NULL,
    plus DOUBLE NOT NULL,
    PRIMARY KEY (idEmpleado),
    CONSTRAINT fk_cocinero_empleado FOREIGN KEY (idEmpleado) 
        REFERENCES empleado(idEmpleado) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Subclase CAJERO (Joined)
CREATE TABLE cajero (
    idEmpleado BIGINT NOT NULL,
    turno VARCHAR(50) NOT NULL,
    PRIMARY KEY (idEmpleado),
    CONSTRAINT fk_cajero_empleado FOREIGN KEY (idEmpleado) 
        REFERENCES empleado(idEmpleado) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 2. Tabla PLATO (Sincronizado BIGINT con Plato.java)
CREATE TABLE plato (
    idPlato BIGINT AUTO_INCREMENT, 
    nombre VARCHAR(100) NOT NULL,
    precioVenta DOUBLE NOT NULL,
    costoProduccion DOUBLE NOT NULL,
    PRIMARY KEY (idPlato)
) ENGINE=InnoDB;

-- 3. Tabla FESTIVAL
CREATE TABLE festival (
    idFestival BIGINT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    temporada VARCHAR(50) NOT NULL,
    inicio DATE NOT NULL,
    fin DATE NOT NULL,
    PRIMARY KEY (idFestival)
) ENGINE=InnoDB;

-- 4. Tabla UNIDAD_VENTA (Clase Base)
CREATE TABLE unidad_venta (
    idUnidadVenta BIGINT AUTO_INCREMENT,
    nombreComercial VARCHAR(100) NOT NULL,
    superficie DOUBLE NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    idEmpleado BIGINT NOT NULL,
    PRIMARY KEY (idUnidadVenta),
    CONSTRAINT fk_unidad_venta_responsable FOREIGN KEY (idEmpleado) 
        REFERENCES empleado(idEmpleado) ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Subclase FOOD_TRUCK (Joined)
CREATE TABLE food_truck (
    idUnidadVenta BIGINT NOT NULL,
    patente VARCHAR(20) NOT NULL,
    conexionElectrica BOOLEAN NOT NULL,
    PRIMARY KEY (idUnidadVenta),
    CONSTRAINT fk_foodtruck_unidad FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Subclase PUESTO_DESARMABLE (Joined)
CREATE TABLE puesto_desarmable (
    idUnidadVenta BIGINT NOT NULL,
    cantidadCarpas INT NOT NULL,
    minutos INT NOT NULL,
    PRIMARY KEY (idUnidadVenta),
    CONSTRAINT fk_puesto_unidad FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 5. Tabla PEDIDO
CREATE TABLE pedido (
    idPedido BIGINT AUTO_INCREMENT,
    fecha DATE NOT NULL,
    idFestival BIGINT NOT NULL,
    idUnidadVenta BIGINT NOT NULL,
    PRIMARY KEY (idPedido),
    CONSTRAINT fk_pedido_festival FOREIGN KEY (idFestival) 
        REFERENCES festival(idFestival) ON UPDATE CASCADE,
    CONSTRAINT fk_pedido_unidad_venta FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 6. Tabla VENTA
CREATE TABLE venta (
    idVenta BIGINT AUTO_INCREMENT, 
    cantidad INT NOT NULL,
    idPlato BIGINT NOT NULL,      
    idPedido BIGINT NOT NULL,
    PRIMARY KEY (idVenta),
    CONSTRAINT fk_venta_plato FOREIGN KEY (idPlato) 
        REFERENCES plato(idPlato) ON UPDATE CASCADE,
    CONSTRAINT fk_venta_pedido FOREIGN KEY (idPedido) 
        REFERENCES pedido(idPedido) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 7. TABLAS INTERMEDIAS (Relaciones N:M)

CREATE TABLE festival_unidades_venta (
    idFestival BIGINT NOT NULL,
    idUnidadVenta BIGINT NOT NULL,
    PRIMARY KEY (idFestival, idUnidadVenta),
    CONSTRAINT fk_fuv_festival FOREIGN KEY (idFestival) 
        REFERENCES festival(idFestival) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_fuv_unidad FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE unidad_venta_personal (
    idUnidadVenta BIGINT NOT NULL,
    idEmpleado BIGINT NOT NULL,
    PRIMARY KEY (idUnidadVenta, idEmpleado),
    CONSTRAINT fk_uvp_unidad FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_uvp_empleado FOREIGN KEY (idEmpleado) 
        REFERENCES empleado(idEmpleado) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE unidad_venta_plato (
    idUnidadVenta BIGINT NOT NULL,
    idPlato BIGINT NOT NULL, 
    PRIMARY KEY (idUnidadVenta, idPlato),
    CONSTRAINT fk_uvplato_unidad FOREIGN KEY (idUnidadVenta) 
        REFERENCES unidad_venta(idUnidadVenta) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_uvplato_plato FOREIGN KEY (idPlato) 
        REFERENCES plato(idPlato) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
>>>>>>> c8e297fe96c8bc76cef998c427cea5863f7bc759
