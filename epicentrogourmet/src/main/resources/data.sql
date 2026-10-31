-- ============================================================
-- EPICENTRO GOURMET
-- SCRIPT UNIFICADO DE DATOS DE PRUEBA
-- ============================================================
--
-- IMPORTANTE:
-- 1. Ejecutar la aplicación al menos una vez antes de este script.
--    Spring crea las tablas y el usuario ADMINISTRADOR.
-- 2. El administrador existente se conserva.
-- 3. Los usuarios precargados copian la contraseña BCrypt
--    del administrador para facilitar las pruebas.
--
-- DATOS ADICIONALES:
-- - Se conservan los 17 pedidos del festival (10/11 al 15/11)
--   para probar Rentabilidad.
-- - Se agrega Borgesa como unidad de prueba para verificar su canon base.
-- - Se agregan 6 pedidos del 27/10 al 29/10 para probar Cierre de Caja
--   con fechas pasadas (2 pedidos por día).
-- - Los pedidos de octubre se asocian a la unidad 1 y al festival 1,
--   aunque sus fechas son anteriores al período oficial del festival.
--   Sirven para probar el cierre de caja, no la rentabilidad del festival.
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
-- Se toma directamente el hash BCrypt del administrador.
-- Si el administrador utiliza Demo123!, los demás usuarios
-- precargados también utilizarán Demo123!.
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

-- FESTIVAL 1: Festival Gourmet de Lanús (id_costos = 1)
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

-- FESTIVAL 2: Sabores de Invierno (id_costos = 2)
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

-- FESTIVAL 3: Festival de Verano (id_costos = 3)
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

-- FESTIVAL PRINCIPAL (id_festival = 1)
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

-- FESTIVAL HISTÓRICO (id_festival = 2)
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

-- FESTIVAL FUTURO (id_festival = 3)
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

-- EMPLEADO 1: Roberto Gómez (Responsable de Burger Lanús)
INSERT INTO empleado (
    activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre
)
VALUES (
    1, 'Gómez', 30123456, 'rgomez@mail.com',
    '2025-01-10', '1990-05-15', 'Roberto'
);

-- EMPLEADO 2: Camila López (Cajera de Burger Lanús)
INSERT INTO empleado (
    activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre
)
VALUES (
    1, 'López', 40123456, 'clopez@mail.com',
    '2026-02-01', '2000-08-20', 'Camila'
);

-- EMPLEADO 3: Marcos Díaz (Cocinero de Burger Lanús)
INSERT INTO empleado (
    activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre
)
VALUES (
    1, 'Díaz', 35123456, 'mdiaz@mail.com',
    '2026-03-15', '1995-11-10', 'Marcos'
);

-- EMPLEADO 4: Ana Torres (Responsable de Tacos El Rey)
INSERT INTO empleado (
    activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre
)
VALUES (
    1, 'Torres', 38123456, 'atorres@mail.com',
    '2026-01-20', '1992-07-25', 'Ana'
);

-- ============================================================
-- 4. SUBTIPOS DE EMPLEADO
-- ============================================================

-- Roberto Gómez: RESPONSABLE
INSERT INTO responsable (id_empleado) VALUES (1);

-- Camila López: CAJERA
INSERT INTO cajero (turno, id_empleado) VALUES ('MANANA', 2);

-- Marcos Díaz: COCINERO
INSERT INTO cocinero (rol_cocina, id_empleado) VALUES ('COCINERO', 3);

-- Ana Torres: RESPONSABLE
INSERT INTO responsable (id_empleado) VALUES (4);

-- ============================================================
-- 5. UNIDADES DE VENTA
-- ============================================================

-- UNIDAD 1: Burger Lanús - Responsable: Roberto Gómez
INSERT INTO unidad_venta (
    codigo, nombre_comercial, superficie, id_festival, id_responsable
)
VALUES (
    'FOODTRK001', 'Burger Lanús', 15.5, 1, 1
);

INSERT INTO food_truck (
    conexion_electrica, patente, id_unidad_venta
)
VALUES (
    1, 'AE123FG', 1
);

-- UNIDAD 2: Tacos El Rey - Responsable: Ana Torres
INSERT INTO unidad_venta (
    codigo, nombre_comercial, superficie, id_festival, id_responsable
)
VALUES (
    'PUEST00002', 'Tacos El Rey', 20.0, 1, 4
);

INSERT INTO puesto_desarmable (
    cantidad_carpas, minutos, id_unidad_venta
)
VALUES (
    2, 120, 2
);

-- UNIDAD ADICIONAL PARA PRUEBAR EL CANON DE BORGESA
-- Superficie: 30 m2. Food Truck con conexión eléctrica.
-- Canon base esperado: 30 * 5.000 + 10.000 = $160.000.
-- El responsable asignado es Roberto (ID 1), para no crear otro empleado.
INSERT INTO unidad_venta (
    codigo, nombre_comercial, superficie, id_festival, id_responsable
)
VALUES (
    'BORGESA233', 'Borgesa', 30.0, 1, 1
);

INSERT INTO food_truck (
    conexion_electrica, patente, id_unidad_venta
)
VALUES (
    1, 'AB456CD', 3
);

-- ============================================================
-- 6. USUARIOS
-- ============================================================

-- Roberto Gómez: RESPONSABLE
INSERT INTO usuario (
    activo, password, rol, username, id_empleado
)
VALUES (
    1, @ADMIN_PASSWORD_HASH, 'RESPONSABLE', 'rgomez@mail.com', 1
);

-- Ana Torres: RESPONSABLE
INSERT INTO usuario (
    activo, password, rol, username, id_empleado
)
VALUES (
    1, @ADMIN_PASSWORD_HASH, 'RESPONSABLE', 'atorres@mail.com', 4
);

-- Camila López: EMPLEADO
INSERT INTO usuario (
    activo, password, rol, username, id_empleado
)
VALUES (
    1, @ADMIN_PASSWORD_HASH, 'EMPLEADO', 'clopez@mail.com', 2
);

-- Marcos Díaz: EMPLEADO
INSERT INTO usuario (
    activo, password, rol, username, id_empleado
)
VALUES (
    1, @ADMIN_PASSWORD_HASH, 'EMPLEADO', 'mdiaz@mail.com', 3
);

-- ============================================================
-- 7. PLATOS
-- ============================================================

-- Burger Lanús
INSERT INTO plato (
    costo_produccion, nombre, precio_venta, id_unidad_venta
)
VALUES (
    8000.00, 'Hamburguesa Doble', 23000.00, 1
);

INSERT INTO plato (
    costo_produccion, nombre, precio_venta, id_unidad_venta
)
VALUES (
    3500.00, 'Papas Cheddar', 12000.00, 1
);

-- Tacos El Rey
INSERT INTO plato (
    costo_produccion, nombre, precio_venta, id_unidad_venta
)
VALUES (
    6000.00, 'Taco al Pastor', 18000.00, 2
);

-- ============================================================
-- 8. ASIGNACIONES DE EMPLEADOS
-- ============================================================

-- Camila López -> Burger Lanús
INSERT INTO asignacion_empleado (
    fecha_desde, fecha_hasta, sueldo_liquidado,
    id_empleado, id_unidad_venta
)
VALUES (
    '2026-11-10', NULL, NULL, 2, 1
);

-- Marcos Díaz -> Burger Lanús
INSERT INTO asignacion_empleado (
    fecha_desde, fecha_hasta, sueldo_liquidado,
    id_empleado, id_unidad_venta
)
VALUES (
    '2026-11-10', NULL, NULL, 3, 1
);

-- ============================================================
-- 9. PEDIDOS
-- Festival Gourmet de Lanús - Burger Lanús
-- ============================================================

-- 10/11/2026: cuatro pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-10', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-10', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-10', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-10', 1, 1);

-- 11/11/2026: dos pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-11', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-11', 1, 1);

-- 12/11/2026: dos pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-12', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-12', 1, 1);

-- 13/11/2026: tres pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-13', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-13', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-13', 1, 1);

-- 14/11/2026: cuatro pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-14', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-14', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-14', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-14', 1, 1);

-- 15/11/2026: dos pedidos
INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-15', 1, 1);

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES ('2026-11-15', 1, 1);

-- ============================================================
-- PEDIDOS ADICIONALES PARA PROBAR CIERRE DE CAJA
-- Dos pedidos por día: 27, 28 y 29 de octubre de 2026.
-- Se insertan después de los 17 pedidos originales.
-- ============================================================

INSERT INTO pedido (fecha, id_festival, id_unidad_venta)
VALUES
    ('2026-10-27', 1, 1),
    ('2026-10-27', 1, 1),
    ('2026-10-28', 1, 1),
    ('2026-10-28', 1, 1),
    ('2026-10-29', 1, 1),
    ('2026-10-29', 1, 1);

-- LAST_INSERT_ID() devuelve el ID del primer pedido del INSERT múltiple.
SET @primerPedidoPrueba = LAST_INSERT_ID();

-- ============================================================
-- 10. ITEMS DE PEDIDO
-- ============================================================

-- PEDIDO 1
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, 1, 1),
    (1, 1, 2);

-- PEDIDO 2
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (1, 2, 1),
    (1, 2, 2);

-- PEDIDO 3
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (4, 3, 1),
    (2, 3, 2);

-- PEDIDO 4
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES (1, 4, 1);

-- PEDIDO 5
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES (2, 5, 1);

-- PEDIDO 6
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (3, 6, 1),
    (2, 6, 2);

-- PEDIDO 7
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, 7, 1),
    (1, 7, 2);

-- PEDIDO 8
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES (1, 8, 1);

-- PEDIDO 9
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (3, 9, 1),
    (2, 9, 2);

-- PEDIDO 10
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, 10, 1),
    (1, 10, 2);

-- PEDIDO 11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (4, 11, 1),
    (3, 11, 2);

-- PEDIDO 12
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (5, 12, 1),
    (3, 12, 2);

-- PEDIDO 13
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (4, 13, 1),
    (4, 13, 2);

-- PEDIDO 14
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, 14, 1),
    (1, 14, 2);

-- PEDIDO 15
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (6, 15, 1),
    (4, 15, 2);

-- PEDIDO 16
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (3, 16, 1),
    (2, 16, 2);

-- PEDIDO 17
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, 17, 1),
    (1, 17, 2);

-- ============================================================
-- ITEMS DE LOS PEDIDOS DE OCTUBRE (18 AL 23)
-- ============================================================

-- PEDIDO 18 - 27/10: 1 hamburguesa + 1 papas = $35.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (1, @primerPedidoPrueba, 1),
    (1, @primerPedidoPrueba, 2);

-- PEDIDO 19 - 27/10: 2 hamburguesas = $46.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, @primerPedidoPrueba + 1, 1);

-- PEDIDO 20 - 28/10: 2 hamburguesas + 2 papas = $70.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (2, @primerPedidoPrueba + 2, 1),
    (2, @primerPedidoPrueba + 2, 2);

-- PEDIDO 21 - 28/10: 1 hamburguesa + 2 papas = $47.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (1, @primerPedidoPrueba + 3, 1),
    (2, @primerPedidoPrueba + 3, 2);

-- PEDIDO 22 - 29/10: 3 hamburguesas = $69.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (3, @primerPedidoPrueba + 4, 1);

-- PEDIDO 23 - 29/10: 1 hamburguesa + 1 papas = $35.000
INSERT INTO item_pedido (cantidad, id_pedido, id_plato)
VALUES
    (1, @primerPedidoPrueba + 5, 1),
    (1, @primerPedidoPrueba + 5, 2);

-- ============================================================
-- 11. CIERRES DE CAJA
-- ============================================================
-- No precargamos cierres manualmente.
-- Los cierres del 27, 28 y 29 de octubre deben registrarse
-- desde la aplicación para comprobar que el monto se calcula
-- automáticamente a partir de los pedidos del día.
-- De esta manera tampoco quedan cierres futuros precargados
-- que impidan crear nuevos cierres cuando comience el festival.
-- ============================================================

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

SELECT 'costos_festival' AS tabla, COUNT(*) AS cantidad FROM costos_festival
UNION ALL
SELECT 'festival', COUNT(*) FROM festival
UNION ALL
SELECT 'empleado', COUNT(*) FROM empleado
UNION ALL
SELECT 'responsable', COUNT(*) FROM responsable
UNION ALL
SELECT 'usuario', COUNT(*) FROM usuario
UNION ALL
SELECT 'cocinero', COUNT(*) FROM cocinero
UNION ALL
SELECT 'cajero', COUNT(*) FROM cajero
UNION ALL
SELECT 'unidad_venta', COUNT(*) FROM unidad_venta
UNION ALL
SELECT 'plato', COUNT(*) FROM plato
UNION ALL
SELECT 'asignacion_empleado', COUNT(*) FROM asignacion_empleado
UNION ALL
SELECT 'pedido', COUNT(*) FROM pedido
UNION ALL
SELECT 'item_pedido', COUNT(*) FROM item_pedido
UNION ALL
SELECT 'cierre_caja', COUNT(*) FROM cierre_caja;

-- ============================================================
-- 14. VERIFICACIÓN DE RECAUDACIÓN PARA CIERRE DE CAJA
-- ============================================================

SELECT
    p.fecha,
    COUNT(DISTINCT p.id_pedido) AS cantidad_pedidos,
    SUM(ip.cantidad * pl.precio_venta) AS recaudacion_total
FROM pedido p
INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido
INNER JOIN plato pl ON ip.id_plato = pl.id_plato
WHERE p.fecha BETWEEN '2026-10-27' AND '2026-10-29'
  AND p.id_unidad_venta = 1
GROUP BY p.fecha
ORDER BY p.fecha;

-- Resultados esperados:
-- 27/10/2026: $81.000,00
-- 28/10/2026: $117.000,00
-- 29/10/2026: $104.000,00

-- ============================================================
-- 15. VERIFICACIÓN DE PEDIDOS PARA RENTABILIDAD DEL FESTIVAL
-- ============================================================

SELECT
    p.fecha,
    COUNT(DISTINCT p.id_pedido) AS cantidad_pedidos,
    SUM(ip.cantidad * pl.precio_venta) AS ingresos,
    SUM(ip.cantidad * pl.costo_produccion) AS costos_produccion,
    SUM(ip.cantidad * (pl.precio_venta - pl.costo_produccion)) AS margen_antes_de_sueldos_y_canon
FROM pedido p
INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido
INNER JOIN plato pl ON ip.id_plato = pl.id_plato
WHERE p.fecha BETWEEN '2026-11-10' AND '2026-11-15'
  AND p.id_unidad_venta = 1
GROUP BY p.fecha
ORDER BY p.fecha;

-- ============================================================
-- 16. VERIFICACIÓN DEL CANON DE BORGESA
-- ============================================================
-- Unidad 3: Borgesa, Food Truck de 30 m2 con electricidad.
-- Canon esperado: 30 * $5.000 + $10.000 = $160.000.
-- ============================================================

SELECT
    uv.id_unidad_venta,
    uv.nombre_comercial,
    uv.superficie,
    cf.costo_por_superficie,
    cf.plus_electricidad,
    (uv.superficie * cf.costo_por_superficie + cf.plus_electricidad) AS canon_base
FROM unidad_venta uv
INNER JOIN festival f ON uv.id_festival = f.id_festival
INNER JOIN costos_festival cf ON f.id_costos = cf.id_costos
INNER JOIN food_truck ft ON uv.id_unidad_venta = ft.id_unidad_venta
WHERE uv.codigo = 'BORGESA233'
  AND ft.conexion_electrica = 1;

SET SQL_SAFE_UPDATES = 1;

-- ============================================================
-- FIN DEL SCRIPT
-- ============================================================
