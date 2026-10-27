-- ============================================================
-- EPICENTRO GOURMET
-- DATOS DE PRUEBA UNIFICADOS
-- ============================================================
--
-- Este script deja la base en un estado conocido para que
-- todos los integrantes trabajen con los mismos datos.
--
-- IMPORTANTE:
-- Ejecutar sobre el schema epicentrogourmet.
-- ============================================================


USE epicentrogourmet;

SET SQL_SAFE_UPDATES = 0;


-- ============================================================
-- 0. LIMPIEZA
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;


DELETE FROM item_pedido;
DELETE FROM cierre_caja;
DELETE FROM pedido;
DELETE FROM plato;
DELETE FROM asignacion_empleado;
DELETE FROM usuario;

DELETE FROM food_truck;
DELETE FROM puesto_desarmable;
DELETE FROM unidad_venta;

DELETE FROM cajero;
DELETE FROM cocinero;
DELETE FROM empleado;

DELETE FROM festival;
DELETE FROM costos_festival;


-- ============================================================
-- REINICIO DE IDS
-- ============================================================

ALTER TABLE item_pedido AUTO_INCREMENT = 1;
ALTER TABLE cierre_caja AUTO_INCREMENT = 1;
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE plato AUTO_INCREMENT = 1;
ALTER TABLE asignacion_empleado AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;

ALTER TABLE unidad_venta AUTO_INCREMENT = 1;
ALTER TABLE empleado AUTO_INCREMENT = 1;

ALTER TABLE festival AUTO_INCREMENT = 1;
ALTER TABLE costos_festival AUTO_INCREMENT = 1;


SET FOREIGN_KEY_CHECKS = 1;


-- ============================================================
-- 1. COSTOS DE FESTIVALES
-- ============================================================


-- ------------------------------------------------------------
-- COSTOS FESTIVAL GOURMET DE LANUS
-- id_costos = 1
-- ------------------------------------------------------------

INSERT INTO costos_festival (
    costo_por_montaje,
    costo_por_superficie,
    plus_ayudante,
    plus_cocinero,
    plus_electricidad,
    plus_lavaplatos,
    sueldo_base,
    valor_por_anio_antiguedad
)
VALUES (
    15000.00,
    5000.00,
    20000.00,
    30000.00,
    10000.00,
    15000.00,
    350000.00,
    5000.00
);


-- ------------------------------------------------------------
-- COSTOS SABORES DE INVIERNO
-- id_costos = 2
-- ------------------------------------------------------------

INSERT INTO costos_festival (
    costo_por_montaje,
    costo_por_superficie,
    plus_ayudante,
    plus_cocinero,
    plus_electricidad,
    plus_lavaplatos,
    sueldo_base,
    valor_por_anio_antiguedad
)
VALUES (
    12000.00,
    4500.00,
    18000.00,
    28000.00,
    9000.00,
    14000.00,
    340000.00,
    4500.00
);


-- ------------------------------------------------------------
-- COSTOS FESTIVAL DE VERANO
-- id_costos = 3
-- ------------------------------------------------------------

INSERT INTO costos_festival (
    costo_por_montaje,
    costo_por_superficie,
    plus_ayudante,
    plus_cocinero,
    plus_electricidad,
    plus_lavaplatos,
    sueldo_base,
    valor_por_anio_antiguedad
)
VALUES (
    17000.00,
    5500.00,
    22000.00,
    32000.00,
    12000.00,
    16000.00,
    360000.00,
    5500.00
);


-- ============================================================
-- 2. FESTIVALES
-- ============================================================


-- ------------------------------------------------------------
-- FESTIVAL PRINCIPAL
-- Utilizado por las Unidades, Pedidos y Cierres de Caja
-- id_festival = 1
-- ------------------------------------------------------------

INSERT INTO festival (
    fecha_inicio,
    fecha_fin,
    nombre,
    temporada,
    id_costos
)
VALUES (
    '2026-11-10',
    '2026-11-15',
    'Festival Gourmet de Lanús',
    'Primavera 2026',
    1
);


-- ------------------------------------------------------------
-- FESTIVAL HISTORICO
-- id_festival = 2
-- ------------------------------------------------------------

INSERT INTO festival (
    fecha_inicio,
    fecha_fin,
    nombre,
    temporada,
    id_costos
)
VALUES (
    '2026-07-10',
    '2026-07-20',
    'Sabores de Invierno',
    'Invierno 2026',
    2
);


-- ------------------------------------------------------------
-- FESTIVAL FUTURO
-- id_festival = 3
-- ------------------------------------------------------------

INSERT INTO festival (
    fecha_inicio,
    fecha_fin,
    nombre,
    temporada,
    id_costos
)
VALUES (
    '2027-01-15',
    '2027-01-25',
    'Festival de Verano',
    'Verano 2027',
    3
);


-- ============================================================
-- 3. EMPLEADOS
-- ============================================================


-- ------------------------------------------------------------
-- Roberto Gomez
-- Responsable de Burger Lanus
-- id_empleado = 1
-- ------------------------------------------------------------

INSERT INTO empleado (
    activo,
    apellido,
    dni,
    email,
    fecha_ingreso,
    fecha_nacimiento,
    nombre
)
VALUES (
    1,
    'Gómez',
    30123456,
    'rgomez@mail.com',
    '2025-01-10',
    '1990-05-15',
    'Roberto'
);


-- ------------------------------------------------------------
-- Camila Lopez
-- Cajera de Burger Lanus
-- id_empleado = 2
-- ------------------------------------------------------------

INSERT INTO empleado (
    activo,
    apellido,
    dni,
    email,
    fecha_ingreso,
    fecha_nacimiento,
    nombre
)
VALUES (
    1,
    'López',
    40123456,
    'clopez@mail.com',
    '2026-02-01',
    '2000-08-20',
    'Camila'
);


-- ------------------------------------------------------------
-- Marcos Diaz
-- Cocinero de Burger Lanus
-- id_empleado = 3
-- ------------------------------------------------------------

INSERT INTO empleado (
    activo,
    apellido,
    dni,
    email,
    fecha_ingreso,
    fecha_nacimiento,
    nombre
)
VALUES (
    1,
    'Díaz',
    35123456,
    'mdiaz@mail.com',
    '2026-03-15',
    '1995-11-10',
    'Marcos'
);


-- ------------------------------------------------------------
-- Ana Torres
-- Responsable de Tacos El Rey
-- id_empleado = 4
-- ------------------------------------------------------------

INSERT INTO empleado (
    activo,
    apellido,
    dni,
    email,
    fecha_ingreso,
    fecha_nacimiento,
    nombre
)
VALUES (
    1,
    'Torres',
    38123456,
    'atorres@mail.com',
    '2026-01-20',
    '1992-07-25',
    'Ana'
);


-- ============================================================
-- 4. TIPOS DE EMPLEADO
-- ============================================================


-- Camila Lopez - Cajera

INSERT INTO cajero (
    turno,
    id_empleado
)
VALUES (
    'MANANA',
    2
);


-- Marcos Diaz - Cocinero

INSERT INTO cocinero (
    rol_cocina,
    id_empleado
)
VALUES (
    'COCINERO',
    3
);


-- ============================================================
-- 5. UNIDADES DE VENTA
-- ============================================================


-- ------------------------------------------------------------
-- BURGER LANUS
-- id_unidad_venta = 1
-- Responsable: Roberto Gomez
-- Festival: Festival Gourmet de Lanus
-- ------------------------------------------------------------

INSERT INTO unidad_venta (
    codigo,
    nombre_comercial,
    superficie,
    id_festival,
    id_responsable
)
VALUES (
    'FOODTRK001',
    'Burger Lanús',
    15.5,
    1,
    1
);


INSERT INTO food_truck (
    conexion_electrica,
    patente,
    id_unidad_venta
)
VALUES (
    1,
    'AE123FG',
    1
);


-- ------------------------------------------------------------
-- TACOS EL REY
-- id_unidad_venta = 2
-- Responsable: Ana Torres
-- Festival: Festival Gourmet de Lanus
-- ------------------------------------------------------------

INSERT INTO unidad_venta (
    codigo,
    nombre_comercial,
    superficie,
    id_festival,
    id_responsable
)
VALUES (
    'PUEST00002',
    'Tacos El Rey',
    20.0,
    1,
    4
);


INSERT INTO puesto_desarmable (
    cantidad_carpas,
    minutos,
    id_unidad_venta
)
VALUES (
    2,
    120,
    2
);


-- ============================================================
-- 6. USUARIOS DE PRUEBA
-- ============================================================
--
-- ADMINISTRADOR
-- Usuario: admin
-- Clave: Admin123!
--
-- RESPONSABLE ROBERTO
-- Usuario: rgomez
-- Clave: Resp123!
--
-- RESPONSABLE ANA
-- Usuario: atorres
-- Clave: Resp123!
--
-- EMPLEADO CAMILA
-- Usuario: clopez
-- Clave: Empleado123!
--
-- EMPLEADO MARCOS
-- Usuario: mdiaz
-- Clave: Empleado123!
--
-- Las claves se almacenan como hash BCrypt.
-- ============================================================


-- ------------------------------------------------------------
-- ADMINISTRADOR
-- ------------------------------------------------------------

INSERT INTO usuario (
    activo,
    password,
    rol,
    username,
    id_empleado
)
VALUES (
    1,
    '$2y$10$QKbLxmzbkKRqam0Dzt3qNuK6R1bC4p4kA4NB/2GF1YSLtybGRPzAe',
    'ADMINISTRADOR',
    'admin',
    NULL
);


-- ------------------------------------------------------------
-- RESPONSABLE ROBERTO GOMEZ
-- ------------------------------------------------------------

INSERT INTO usuario (
    activo,
    password,
    rol,
    username,
    id_empleado
)
VALUES (
    1,
    '$2y$10$59yH6WBv.eWDfFFM5K97D.D/woiBpTJgleFxvV06JaIMYanwVaDgy',
    'RESPONSABLE',
    'rgomez',
    1
);


-- ------------------------------------------------------------
-- RESPONSABLE ANA TORRES
-- ------------------------------------------------------------

INSERT INTO usuario (
    activo,
    password,
    rol,
    username,
    id_empleado
)
VALUES (
    1,
    '$2y$10$59yH6WBv.eWDfFFM5K97D.D/woiBpTJgleFxvV06JaIMYanwVaDgy',
    'RESPONSABLE',
    'atorres',
    4
);


-- ------------------------------------------------------------
-- EMPLEADO CAMILA LOPEZ
-- ------------------------------------------------------------

INSERT INTO usuario (
    activo,
    password,
    rol,
    username,
    id_empleado
)
VALUES (
    1,
    '$2y$10$nURAOSNKjAespVpyyX7Nd.kX.CeIRlEWMexrS7gHndo/BWGALk3Nu',
    'EMPLEADO',
    'clopez',
    2
);


-- ------------------------------------------------------------
-- EMPLEADO MARCOS DIAZ
-- ------------------------------------------------------------

INSERT INTO usuario (
    activo,
    password,
    rol,
    username,
    id_empleado
)
VALUES (
    1,
    '$2y$10$nURAOSNKjAespVpyyX7Nd.kX.CeIRlEWMexrS7gHndo/BWGALk3Nu',
    'EMPLEADO',
    'mdiaz',
    3
);


-- ============================================================
-- 7. PLATOS
-- ============================================================


-- ------------------------------------------------------------
-- PLATOS BURGER LANUS
-- ------------------------------------------------------------

INSERT INTO plato (
    costo_produccion,
    nombre,
    precio_venta,
    id_unidad_venta
)
VALUES (
    8000.00,
    'Hamburguesa Doble',
    23000.00,
    1
);


INSERT INTO plato (
    costo_produccion,
    nombre,
    precio_venta,
    id_unidad_venta
)
VALUES (
    3500.00,
    'Papas Cheddar',
    12000.00,
    1
);


-- ------------------------------------------------------------
-- PLATOS TACOS EL REY
-- ------------------------------------------------------------

INSERT INTO plato (
    costo_produccion,
    nombre,
    precio_venta,
    id_unidad_venta
)
VALUES (
    6000.00,
    'Taco al Pastor',
    18000.00,
    2
);


-- ============================================================
-- 8. ASIGNACIONES DE EMPLEADOS
-- ============================================================


-- ------------------------------------------------------------
-- Camila Lopez -> Burger Lanus
-- ------------------------------------------------------------

INSERT INTO asignacion_empleado (
    fecha_desde,
    fecha_hasta,
    sueldo_liquidado,
    id_empleado,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    NULL,
    NULL,
    2,
    1
);


-- ------------------------------------------------------------
-- Marcos Diaz -> Burger Lanus
-- ------------------------------------------------------------

INSERT INTO asignacion_empleado (
    fecha_desde,
    fecha_hasta,
    sueldo_liquidado,
    id_empleado,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    NULL,
    NULL,
    3,
    1
);


-- ============================================================
-- 9. PEDIDOS - BURGER LANUS
-- ============================================================


-- ------------------------------------------------------------
-- 10/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-10',
    1,
    1
);


-- ------------------------------------------------------------
-- 11/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-11',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-11',
    1,
    1
);


-- ------------------------------------------------------------
-- 12/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-12',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-12',
    1,
    1
);


-- ------------------------------------------------------------
-- 13/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-13',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-13',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-13',
    1,
    1
);


-- ------------------------------------------------------------
-- 14/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-14',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-14',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-14',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-14',
    1,
    1
);


-- ------------------------------------------------------------
-- 15/11/2026
-- ------------------------------------------------------------

INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-15',
    1,
    1
);


INSERT INTO pedido (
    fecha,
    id_festival,
    id_unidad_venta
)
VALUES (
    '2026-11-15',
    1,
    1
);


-- ============================================================
-- 10. ITEMS DE PEDIDOS
-- ============================================================


-- ------------------------------------------------------------
-- PEDIDO 1
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 1, 1),
    (1, 1, 2);


-- ------------------------------------------------------------
-- PEDIDO 2
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (1, 2, 1),
    (1, 2, 2);


-- ------------------------------------------------------------
-- PEDIDO 3
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 3, 1),
    (2, 3, 2);


-- ------------------------------------------------------------
-- PEDIDO 4
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES (
    1,
    4,
    1
);


-- ------------------------------------------------------------
-- PEDIDO 5
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES (
    2,
    5,
    1
);


-- ------------------------------------------------------------
-- PEDIDO 6
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 6, 1),
    (2, 6, 2);


-- ------------------------------------------------------------
-- PEDIDO 7
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 7, 1),
    (1, 7, 2);


-- ------------------------------------------------------------
-- PEDIDO 8
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES (
    1,
    8,
    1
);


-- ------------------------------------------------------------
-- PEDIDO 9
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 9, 1),
    (2, 9, 2);


-- ------------------------------------------------------------
-- PEDIDO 10
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 10, 1),
    (1, 10, 2);


-- ------------------------------------------------------------
-- PEDIDO 11
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 11, 1),
    (3, 11, 2);


-- ------------------------------------------------------------
-- PEDIDO 12
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (5, 12, 1),
    (3, 12, 2);


-- ------------------------------------------------------------
-- PEDIDO 13
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 13, 1),
    (4, 13, 2);


-- ------------------------------------------------------------
-- PEDIDO 14
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 14, 1),
    (1, 14, 2);


-- ------------------------------------------------------------
-- PEDIDO 15
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (6, 15, 1),
    (4, 15, 2);


-- ------------------------------------------------------------
-- PEDIDO 16
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 16, 1),
    (2, 16, 2);


-- ------------------------------------------------------------
-- PEDIDO 17
-- ------------------------------------------------------------

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 17, 1),
    (1, 17, 2);


-- ============================================================
-- 11. CIERRES DE CAJA
-- ============================================================

INSERT INTO cierre_caja (
    fecha,
    monto_total,
    id_cajero,
    id_unidad_venta
)
VALUES
    ('2026-11-10', 232000.00, 2, 1),
    ('2026-11-11', 139000.00, 2, 1),
    ('2026-11-12', 81000.00, 2, 1),
    ('2026-11-13', 279000.00, 2, 1),
    ('2026-11-14', 535000.00, 2, 1),
    ('2026-11-15', 151000.00, 2, 1);


-- ============================================================
-- 12. VERIFICACION FINAL
-- ============================================================

SELECT
    'costos_festival' AS tabla,
    COUNT(*) AS cantidad
FROM costos_festival

UNION ALL

SELECT
    'festival',
    COUNT(*)
FROM festival

UNION ALL

SELECT
    'empleado',
    COUNT(*)
FROM empleado

UNION ALL

SELECT
    'usuario',
    COUNT(*)
FROM usuario

UNION ALL

SELECT
    'unidad_venta',
    COUNT(*)
FROM unidad_venta

UNION ALL

SELECT
    'plato',
    COUNT(*)
FROM plato

UNION ALL

SELECT
    'asignacion_empleado',
    COUNT(*)
FROM asignacion_empleado

UNION ALL

SELECT
    'pedido',
    COUNT(*)
FROM pedido

UNION ALL

SELECT
    'item_pedido',
    COUNT(*)
FROM item_pedido

UNION ALL

SELECT
    'cierre_caja',
    COUNT(*)
FROM cierre_caja;


-- ============================================================
-- RESTAURAR SAFE UPDATES
-- ============================================================

SET SQL_SAFE_UPDATES = 1;


-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================