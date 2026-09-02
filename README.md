# TPGrupalOO2Hito1Grupo06
TP Programación Orientada a Objetos 2 UNLa - Primer entrega
# Integrantes

- Alejandro Escalier - GitHub: cocoQuemad0
- Cristian Medina - GitHub: Medina94
- Valentina Ponzo - GitHub: valentinaponzo
- José Arratia - GitHub: josearratia03
- Cristian Prantera - GitHub: cristianprantera


## Casos de Uso

# Ponzo Valentina

- **Actualizar Festival**
  Consulta un Festival existente, modifica sus datos y actualiza el registro en la base de datos mediante Hibernate.

- **Consultar Festival y sus Unidades de Venta**
  Consulta un Festival junto con todas sus Unidades de Venta.
  Relación: **Uno a Muchos (1:N)** entre `Festival` y `UnidadVenta`.

- **Consultar herencia de Empleado**
  Consulta empleados y determina si corresponden a un `Cocinero` o un `Cajero`.
  Relación: **Herencia** entre `Empleado`, `Cocinero` y `Cajero`.

- **Consultar Unidad de Venta por código**
  Consulta una Unidad de Venta por su código y determina si corresponde a un `FoodTruck` o un `PuestoDesarmable`.
  Relación: **Herencia** entre `UnidadVenta`, `FoodTruck` y `PuestoDesarmable`.


# Medina Cristian

- **Consultar Pedidos filtrado por festiva y fecha**
  Consulta el listado de pedidos correspondientes a un Festival a partir de su Id y por fecha de pedido.
  Relación: **Uno a Muchos** entre `Festival` y `Pedido`.

- **Consultar Platos por Festival**
  Consulta los platos correspondientes a un Festival a partir de su Id.
  Relación: **Uno a Muchos** entre `Festival` y `Platos`.