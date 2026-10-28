-- ============================================================
-- EPICENTRO GOURMET
-- SCRIPT UNIFICADO DE DATOS DE PRUEBA
-- ============================================================
--
-- IMPORTANTE:
-- 1. Ejecutar la aplicación al menos una vez antes de este script.
--    Spring crea las tablas y el usuario ADMINISTRADOR.
--
-- 2. El administrador existente se conserva.
--
-- 3. Los usuarios precargados copian la contraseña BCrypt
--    del administrador para facilitar las pruebas.
--
-- ============================================================


USE epicentrogourmet;

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS = 0;


-- ============================================================
-- 0. LIMPIEZA DE DATOS
-- ============================================================

DELETE FROM item_pedido;
DELETE FROM cierre_caja;
DELETE FROM pedido;
DELETE FROM plato;
DELETE FROM asignacion_empleado;


-- Conservamos únicamente al usuario ADMINISTRADOR.
-- Se eliminan responsables y empleados de prueba anteriores.

DELETE FROM usuario
WHERE rol <> 'ADMINISTRADOR';


-- Primero eliminamos los tipos concretos de Unidad de Venta.

DELETE FROM food_truck;
DELETE FROM puesto_desarmable;


-- Después eliminamos las Unidades de Venta.

DELETE FROM unidad_venta;


-- Eliminamos los subtipos de Empleado.

DELETE FROM cajero;
DELETE FROM cocinero;
DELETE FROM responsable;


-- Finalmente eliminamos los empleados base.

DELETE FROM empleado;


-- Eliminamos festivales y costos.

DELETE FROM festival;
DELETE FROM costos_festival;


-- ============================================================
-- REINICIO DE AUTOINCREMENTALES
-- ============================================================

ALTER TABLE item_pedido AUTO_INCREMENT = 1;
ALTER TABLE cierre_caja AUTO_INCREMENT = 1;
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE plato AUTO_INCREMENT = 1;
ALTER TABLE asignacion_empleado AUTO_INCREMENT = 1;

ALTER TABLE unidad_venta AUTO_INCREMENT = 1;
ALTER TABLE empleado AUTO_INCREMENT = 1;

ALTER TABLE festival AUTO_INCREMENT = 1;
ALTER TABLE costos_festival AUTO_INCREMENT = 1;


SET FOREIGN_KEY_CHECKS = 1;


-- ============================================================
-- CONTRASEÑA DE LOS USUARIOS DE PRUEBA
-- ============================================================
--
-- Se toma directamente el hash BCrypt del administrador.
--
-- De esta manera evitamos guardar hashes inventados o incorrectos
-- en el script.
--
-- Si el administrador utiliza Demo123!, los demás usuarios
-- precargados también utilizarán Demo123!.
--
-- ============================================================

SET @ADMIN_PASSWORD_HASH = (
    SELECT password
    FROM usuario
    WHERE rol = 'ADMINISTRADOR'
    ORDER BY id_usuario
    LIMIT 1
);


-- ============================================================
-- 1. COSTOS DE LOS FESTIVALES
-- ============================================================


-- ------------------------------------------------------------
-- FESTIVAL 1
-- Festival Gourmet de Lanús
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
-- FESTIVAL 2
-- Sabores de Invierno
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
-- FESTIVAL 3
-- Festival de Verano
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
-- FESTIVAL HISTÓRICO
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
--
-- La tabla empleado contiene los datos generales.
--
-- Luego cada persona tiene su subtipo correspondiente:
--
-- Roberto -> Responsable
-- Camila  -> Cajero
-- Marcos  -> Cocinero
-- Ana     -> Responsable
--
-- ============================================================


-- ------------------------------------------------------------
-- EMPLEADO 1
-- Roberto Gómez
-- Responsable de Burger Lanús
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
-- EMPLEADO 2
-- Camila López
-- Cajera de Burger Lanús
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
-- EMPLEADO 3
-- Marcos Díaz
-- Cocinero de Burger Lanús
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
-- EMPLEADO 4
-- Ana Torres
-- Responsable de Tacos El Rey
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
-- 4. SUBTIPOS DE EMPLEADO
-- ============================================================
--
-- MODELO:
--
-- Empleado
-- ├── Responsable
-- ├── Cajero
-- └── Cocinero
--
-- ============================================================


-- ------------------------------------------------------------
-- Roberto Gómez
-- RESPONSABLE
-- ------------------------------------------------------------

INSERT INTO responsable (
    id_empleado
)
VALUES (
    1
);


-- ------------------------------------------------------------
-- Camila López
-- CAJERA
-- ------------------------------------------------------------

INSERT INTO cajero (
    turno,
    id_empleado
)
VALUES (
    'MANANA',
    2
);


-- ------------------------------------------------------------
-- Marcos Díaz
-- COCINERO
-- ------------------------------------------------------------

INSERT INTO cocinero (
    rol_cocina,
    id_empleado
)
VALUES (
    'COCINERO',
    3
);


-- ------------------------------------------------------------
-- Ana Torres
-- RESPONSABLE
-- ------------------------------------------------------------

INSERT INTO responsable (
    id_empleado
)
VALUES (
    4
);


-- ============================================================
-- 5. UNIDADES DE VENTA
-- ============================================================


-- ------------------------------------------------------------
-- UNIDAD 1
-- Burger Lanús
-- Responsable: Roberto Gómez
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
-- UNIDAD 2
-- Tacos El Rey
-- Responsable: Ana Torres
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
-- 6. USUARIOS
-- ============================================================
--
-- Username = email.
--
-- RESPONSABLES:
-- rgomez@mail.com
-- atorres@mail.com
--
-- EMPLEADOS:
-- clopez@mail.com
-- mdiaz@mail.com
--
-- Todos copian la contraseña BCrypt del ADMINISTRADOR.
--
-- ============================================================


-- ------------------------------------------------------------
-- Roberto Gómez
-- RESPONSABLE
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
    @ADMIN_PASSWORD_HASH,
    'RESPONSABLE',
    'rgomez@mail.com',
    1
);


-- ------------------------------------------------------------
-- Ana Torres
-- RESPONSABLE
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
    @ADMIN_PASSWORD_HASH,
    'RESPONSABLE',
    'atorres@mail.com',
    4
);


-- ------------------------------------------------------------
-- Camila López
-- EMPLEADO
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
    @ADMIN_PASSWORD_HASH,
    'EMPLEADO',
    'clopez@mail.com',
    2
);


-- ------------------------------------------------------------
-- Marcos Díaz
-- EMPLEADO
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
    @ADMIN_PASSWORD_HASH,
    'EMPLEADO',
    'mdiaz@mail.com',
    3
);


-- ============================================================
-- 7. PLATOS
-- ============================================================


-- ------------------------------------------------------------
-- Burger Lanús
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
-- Tacos El Rey
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
--
-- IMPORTANTE:
-- Los Responsables NO reciben una AsignacionEmpleado.
--
-- Solamente los empleados operativos:
-- Camila y Marcos.
--
-- ============================================================


-- ------------------------------------------------------------
-- Camila López -> Burger Lanús
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
-- Marcos Díaz -> Burger Lanús
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
-- 9. PEDIDOS
-- Burger Lanús
-- Festival Gourmet de Lanús
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
-- 10. ITEMS DE PEDIDO
-- ============================================================


-- PEDIDO 1

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 1, 1),
    (1, 1, 2);


-- PEDIDO 2

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (1, 2, 1),
    (1, 2, 2);


-- PEDIDO 3

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 3, 1),
    (2, 3, 2);


-- PEDIDO 4

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


-- PEDIDO 5

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


-- PEDIDO 6

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 6, 1),
    (2, 6, 2);


-- PEDIDO 7

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 7, 1),
    (1, 7, 2);


-- PEDIDO 8

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


-- PEDIDO 9

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 9, 1),
    (2, 9, 2);


-- PEDIDO 10

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 10, 1),
    (1, 10, 2);


-- PEDIDO 11

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 11, 1),
    (3, 11, 2);


-- PEDIDO 12

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (5, 12, 1),
    (3, 12, 2);


-- PEDIDO 13

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (4, 13, 1),
    (4, 13, 2);


-- PEDIDO 14

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (2, 14, 1),
    (1, 14, 2);


-- PEDIDO 15

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (6, 15, 1),
    (4, 15, 2);


-- PEDIDO 16

INSERT INTO item_pedido (
    cantidad,
    id_pedido,
    id_plato
)
VALUES
    (3, 16, 1),
    (2, 16, 2);


-- PEDIDO 17

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
--
-- Cajera: Camila López
-- Unidad: Burger Lanús
--
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
-- 12. VERIFICACIÓN DE USUARIOS
-- ============================================================

SELECT
    id_usuario,
    username,
    rol,
    activo,
    id_empleado
FROM usuario
ORDER BY id_usuario;


-- ============================================================
-- 13. VERIFICACIÓN GENERAL
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
    'responsable',
    COUNT(*)
FROM responsable

UNION ALL

SELECT
    'usuario',
    COUNT(*)
FROM usuario

UNION ALL

SELECT
    'cocinero',
    COUNT(*)
FROM cocinero

UNION ALL

SELECT
    'cajero',
    COUNT(*)
FROM cajero

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


SET SQL_SAFE_UPDATES = 1;


-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================