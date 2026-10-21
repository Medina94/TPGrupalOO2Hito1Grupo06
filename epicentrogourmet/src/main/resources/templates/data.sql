-- ==========================================
-- 1. COSTOS Y FESTIVAL
-- ==========================================
INSERT INTO costos_festival (costo_por_montaje, costo_por_superficie, plus_ayudante, plus_cocinero, plus_electricidad, plus_lavaplatos, sueldo_base, valor_por_anio_antiguedad) 
VALUES (15000.00, 5000.00, 20000.00, 30000.00, 10000.00, 15000.00, 350000.00, 5000.00);

INSERT INTO festival (fecha_inicio, fecha_fin, nombre, temporada, id_costos) 
VALUES ('2026-11-10', '2026-11-15', 'Festival Gourmet de Lanús', 'Primavera 2026', 1);

-- ==========================================
-- 2. EMPLEADOS Y ROLES
-- ==========================================
INSERT INTO empleado (activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre) 
VALUES (1, 'Gómez', 30123456, 'rgomez@mail.com', '2025-01-10', '1990-05-15', 'Roberto'); -- id 1 (Resp. Burger)

INSERT INTO empleado (activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre) 
VALUES (1, 'López', 40123456, 'clopez@mail.com', '2026-02-01', '2000-08-20', 'Camila'); -- id 2 (Cajera Burger)

INSERT INTO empleado (activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre) 
VALUES (1, 'Díaz', 35123456, 'mdiaz@mail.com', '2026-03-15', '1995-11-10', 'Marcos'); -- id 3 (Cocinero Burger)

INSERT INTO empleado (activo, apellido, dni, email, fecha_ingreso, fecha_nacimiento, nombre) 
VALUES (1, 'Torres', 38123456, 'atorres@mail.com', '2026-01-20', '1992-07-25', 'Ana'); -- id 4 (Resp. Tacos)

INSERT INTO cajero (turno, id_empleado) VALUES ('MANANA', 2);
INSERT INTO cocinero (rol_cocina, id_empleado) VALUES ('COCINERO', 3);

-- ==========================================
-- 3. UNIDADES DE VENTA
-- ==========================================
INSERT INTO unidad_venta (codigo, nombre_comercial, superficie, id_festival, id_responsable) 
VALUES ('FOODTRK001', 'Burger Lanús', 15.5, 1, 1); -- id 1
INSERT INTO food_truck (conexion_electrica, patente, id_unidad_venta) 
VALUES (1, 'AE123FG', 1);

INSERT INTO unidad_venta (codigo, nombre_comercial, superficie, id_festival, id_responsable) 
VALUES ('PUEST001', 'Tacos El Rey', 20.0, 1, 4); -- id 2
INSERT INTO puesto_desarmable (cantidad_carpas, minutos, id_unidad_venta) 
VALUES (2, 120, 2);

-- ==========================================
-- 4. PLATOS Y PRECIOS REALES
-- ==========================================
INSERT INTO plato (costo_produccion, nombre, precio_venta, id_unidad_venta) 
VALUES (8000.00, 'Hamburguesa Doble', 23000.00, 1); -- id 1

INSERT INTO plato (costo_produccion, nombre, precio_venta, id_unidad_venta) 
VALUES (3500.00, 'Papas Cheddar', 12000.00, 1); -- id 2

INSERT INTO plato (costo_produccion, nombre, precio_venta, id_unidad_venta) 
VALUES (6000.00, 'Taco al Pastor', 18000.00, 2); -- id 3

-- ==========================================
-- 5. ASIGNACIÓN DE EMPLEADOS
-- ==========================================
INSERT INTO asignacion_empleado (fecha_desde, fecha_hasta, sueldo_liquidado, id_empleado, id_unidad_venta) 
VALUES ('2026-11-10', NULL, NULL, 2, 1);
INSERT INTO asignacion_empleado (fecha_desde, fecha_hasta, sueldo_liquidado, id_empleado, id_unidad_venta) 
VALUES ('2026-11-10', NULL, NULL, 3, 1);

-- ==========================================
-- 6. PEDIDOS POR DÍA (Tickets)
-- ==========================================
-- 10/11
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-10', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-10', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-10', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-10', 1, 1); 
-- 11/11
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-11', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-11', 1, 1); 
-- 12/11
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-12', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-12', 1, 1); 
-- 13/11
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-13', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-13', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-13', 1, 1); 
-- 14/11 (Sábado)
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-14', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-14', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-14', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-14', 1, 1); 
-- 15/11 (Domingo)
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-15', 1, 1); 
INSERT INTO pedido (fecha, id_festival, id_unidad_venta) VALUES ('2026-11-15', 1, 1); 

-- ==========================================
-- 7. ITEMS VENDIDOS (Platos por Ticket)
-- ==========================================
-- 10/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 1, 1), (1, 1, 2); 
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (1, 2, 1), (1, 2, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (4, 3, 1), (2, 3, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (1, 4, 1);
-- 11/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 5, 1);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (3, 6, 1), (2, 6, 2);
-- 12/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 7, 1), (1, 7, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (1, 8, 1);
-- 13/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (3, 9, 1), (2, 9, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 10, 1), (1, 10, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (4, 11, 1), (3, 11, 2);
-- 14/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (5, 12, 1), (3, 12, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (4, 13, 1), (4, 13, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 14, 1), (1, 14, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (6, 15, 1), (4, 15, 2);
-- 15/11
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (3, 16, 1), (2, 16, 2);
INSERT INTO item_pedido (cantidad, id_pedido, id_plato) VALUES (2, 17, 1), (1, 17, 2);

-- ==========================================
-- 8. CIERRES DE CAJA (Matemática exacta)
-- ==========================================
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-10', 232000.00, 2, 1);
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-11', 139000.00, 2, 1);
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-12', 81000.00, 2, 1);
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-13', 279000.00, 2, 1);
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-14', 535000.00, 2, 1);
INSERT INTO cierre_caja (fecha, monto_total, id_cajero, id_unidad_venta) VALUES ('2026-11-15', 151000.00, 2, 1);