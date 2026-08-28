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