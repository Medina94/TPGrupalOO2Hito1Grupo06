# TPGrupalOO2Hito1Grupo06

Trabajo Práctico Grupal - Programación Orientada a Objetos II  
Universidad Nacional de Lanús

---

## Integrantes

- Alejandro Escalier - GitHub: `EscalierAlejandro`
- Cristian Medina - GitHub: `Medina94`
- Valentina Ponzo - GitHub: `valentinaponzo`
- José Arratia - GitHub: `josearratia03`
- Cristian Prantera - GitHub: `cristianprantera`

---

# Hito 1

## Casos de Uso

### Ponzo Valentina

#### Actualizar Festival

Consulta un Festival existente, modifica sus datos y actualiza el registro en la base de datos mediante Hibernate.

#### Consultar Festival y sus Unidades de Venta

Consulta un Festival junto con todas sus Unidades de Venta.

Relación: **Uno a Muchos (1:N)** entre `Festival` y `UnidadVenta`.

#### Consultar herencia de Empleado

Consulta empleados y determina si corresponden a un `Cocinero` o un `Cajero`.

Relación: **Herencia** entre `Empleado`, `Cocinero` y `Cajero`.

#### Consultar Unidad de Venta por código

Consulta una Unidad de Venta por su código y determina si corresponde a un `FoodTruck` o un `PuestoDesarmable`.

Relación: **Herencia** entre `UnidadVenta`, `FoodTruck` y `PuestoDesarmable`.

---

### José Arratia

#### Traer Festival por fecha

Se agrega un festival de prueba y luego se lo trae por la fecha de inicio.

---

### Cristian Prantera

#### Actualizar Plato

Consulta un Plato existente por su ID, modifica su precio de venta y actualiza el registro en la base de datos mediante Hibernate.

#### Consultar Platos por precio mayor a

Consulta y filtra una lista de Platos cuyo precio de venta sea superior a un monto específico.

Relación: **Uno a Muchos (1:N)** entre `UnidadVenta` y `Plato`.

---

### Cristian Medina

#### Consultar Pedidos filtrados por Festival y fecha

Consulta el listado de pedidos correspondientes a un Festival a partir de su ID y por fecha de pedido.

Relación: **Uno a Muchos (1:N)** entre `Festival` y `Pedido`.

#### Consultar Platos por Festival

Consulta los platos correspondientes a un Festival a partir de su ID.

Relación: **Uno a Muchos (1:N)** entre `Festival` y `Plato`.

---

### Alejandro Escalier

#### Consultar Empleados de Unidad de Venta

Consulta el listado de empleados correspondientes a una Unidad de Venta a partir de su `idUnidadVenta`.

Relación: **Uno a Muchos (1:N)** entre `UnidadVenta` y `Empleado`.

#### Consultar Platos de Unidad de Venta

Consulta el listado de platos correspondientes a una Unidad de Venta a partir de su `idUnidadVenta`.

Relación: **Uno a Muchos (1:N)** entre `UnidadVenta` y `Plato`.

#### Consultar Pedidos de Unidad de Venta

Consulta el listado de pedidos correspondientes a una Unidad de Venta a partir de su `idUnidadVenta`.

Relación: **Uno a Muchos (1:N)** entre `UnidadVenta` y `Pedido`.

---

# Hito 2 - Epicentro Gourmet

Para el Hito 2 el proyecto fue migrado a una aplicación web desarrollada con:

- Java
- Spring Boot
- Maven
- Spring Data JPA
- Hibernate
- Spring Security
- Thymeleaf
- Jakarta Validation
- MySQL
- BCrypt

---

## Modelo y persistencia

Actualmente se encuentran implementadas las principales entidades del dominio:

- `Festival`
- `CostosFestival`
- `UnidadVenta`
- `FoodTruck`
- `PuestoDesarmable`
- `Empleado`
- `Cocinero`
- `Cajero`
- `Plato`
- `Pedido`
- `ItemPedido`
- `AsignacionEmpleado`
- `Usuario`
- `CierreCaja`

Se implementaron además:

- Relaciones JPA.
- Herencia mediante estrategia `JOINED`.
- Persistencia con MySQL.
- Validaciones mediante Jakarta Validation.
- Roles de usuario.
- Seguridad con Spring Security.
- Contraseñas cifradas con BCrypt.
- Manejo transaccional de operaciones.

---

## Casos de Uso implementados - Hito 2


| Caso de Uso                                 | Responsable        | Estado | Rama | Pull Request                                                          |
|---------------------------------------------|--------------------|---|---|-----------------------------------------------------------------------|
| Inicio de sesión                            | Valentina Ponzo    | Finalizado | `CU-Login` | [PR #5](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/5)   |
| Listado de Festivales                       | Valentina Ponzo    | Finalizado | `CU-Listado-Festivales` | [PR #6](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/6)   |
| Registrar Unidad de Venta + Responsable     | Valentina Ponzo    | Finalizado | `CU-Registrar-Unidad-Responsable` | [PR #7](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/7)   |
| Consultar Unidades por Festival             | Valentina Ponzo    | Finalizado | `CU-Unidades-Por-Festival` | [PR #8](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/8)   |
| Calcular Canon de Unidad de Venta           | Valentina Ponzo    | Finalizado | `CU-Calcular-Canon` | [PR #10](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/10) |
| Alta y Listado de Empleados                 | Alejandro Escalier | Finalizado | `CU-Alta-Listado-Empleados` | [PR #11](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/11) |
| Agregar Platos a Unidad de Venta            | Cristian Medina    | Finalizado | `CU-agregar-platos` | [PR #14](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/14) |
| Reactivación de Empleados                   | Alejandro Escalier | Finalizado | `CU-Reactivacion-Empleados` | [PR #15](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/15) |
| Reporte de Mayores Cánones                  | Valentina Ponzo    | Finalizado | `CU-Reporte-Mayores-Canones` | [PR #16](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/16) |
| Liquidación de Sueldos                      | Valentina Ponzo    | Finalizado | `CU-Liquidacion-Sueldos` | [PR #18](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/18) |
| Panel de inicio del Empleado                | Alejandro Escalier | Finalizado | `CU-Dashboard-Empleado` | [PR #20](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/20) |
| Consultar sueldo por Festival               | Valentina Ponzo    | Finalizado | `CU-Consultar-Sueldo-Festival` | [PR #21](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/21) |
| Consultar Festival y Unidad actual          | Valentina Ponzo    | Finalizado | `CU-Festival-Unidad-Actual-Empleado` | [PR #22](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/22) |
| Consultar historial laboral                 | Valentina Ponzo    | Finalizado | `CU-Historial-Laboral-Empleado` | [PR #23](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/23) |
| Consulta de Empleados con filtros           | Alejandro Escalier | Finalizado | `CU-Consulta-Empleados` | [PR #26](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/26) |
| Envío de mail en alta de usuario            | Cristian Medina    | Finalizado | `CU-mail-alta-empleado` | [PR #27](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/27) |
| Alta de Pedido | Valentina Ponzo | Finalizado | `CU-Alta-Pedido` | [PR #30](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/30) |
| Editar y eliminar platos de unidad de venta | Cristian Medina    | Finalizado | `CU-agregar-platos` | [PR #31](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/31) |
| Envío de mail en reactivación de empleado | Alejandro Escalier | Finalizado | `CU-mail-reactivacion-empleado` | [PR #36](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/36) |
| Listado de Empleados en PDF | Alejandro Escalier | Finalizado | `CU-Listado-Empleados-PDF` | [PR #37](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/37) |

> Cada nuevo Caso de Uso se desarrolla en una rama independiente y posteriormente se integra a `main` mediante Pull Request.

---

## Detalle de Casos de Uso implementados

### CU - Inicio de sesión

Responsable: **Valentina Ponzo**

Funcionalidades:

- Autenticación mediante Spring Security.
- Usuarios persistidos en MySQL.
- Contraseñas cifradas mediante BCrypt.
- Roles:
  - `ADMINISTRADOR`
  - `RESPONSABLE`
  - `EMPLEADO`
- Control de acceso según rol.
- Usuario administrador inicial mediante variables de entorno.
- Pantalla de login personalizada con Thymeleaf y CSS.
- Manejo de credenciales incorrectas.
- Cierre de sesión.

---

### CU - Listado de Festivales

Responsable: **Valentina Ponzo**

Funcionalidades:

- Consulta de festivales almacenados en MySQL.
- Uso de `FestivalRepository`.
- Uso de `FestivalService`.
- Listado ordenado por fecha de inicio.
- Visualización mediante Thymeleaf.
- Visualización del usuario autenticado.
- Acceso al cierre de sesión.
- Manejo de listado vacío.

---

### CU - Registrar Unidad de Venta + Responsable

Responsable: **Valentina Ponzo**

Permite registrar una nueva Unidad de Venta junto con su Responsable.

Tipos de Unidad soportados:

- `FoodTruck`
- `PuestoDesarmable`

Tipos de empleado soportados:

- `Cocinero`
- `Cajero`

Durante el registro también se crea:

- El empleado Responsable.
- El usuario con rol `RESPONSABLE`.
- La contraseña cifrada con BCrypt.
- La Unidad de Venta.
- La asignación del empleado a la Unidad de Venta.

Validaciones implementadas:

- Código de Unidad de Venta único.
- Código de exactamente 10 caracteres.
- DNI válido y no repetido.
- Email válido y no repetido.
- Username único.
- Superficie mayor a cero.
- Responsable mayor de 18 años.
- Fecha de nacimiento válida.
- Fecha de ingreso no futura.
- Validaciones específicas para `FoodTruck`.
- Validaciones específicas para `PuestoDesarmable`.
- Validaciones específicas para `Cocinero`.
- Validaciones específicas para `Cajero`.

La operación se ejecuta de forma transaccional para evitar registros incompletos.

---

### CU - Consultar Unidades por Festival

Responsable: **Valentina Ponzo**

Permite seleccionar un Festival y consultar todas sus Unidades de Venta.

Se visualiza:

- Nombre comercial.
- Tipo de Unidad.
- Código.
- Superficie.
- Responsable.
- Acceso al cálculo de canon.

También se contempla el caso en que el Festival todavía no tenga Unidades de Venta registradas.

---

### CU - Calcular Canon de Unidad de Venta

Responsable: **Valentina Ponzo**

Permite calcular el canon correspondiente a una Unidad de Venta utilizando los costos configurados para el Festival.

El cálculo se encuentra encapsulado en cada tipo de Unidad de Venta.

#### FoodTruck

El canon se calcula considerando:

- Superficie de la Unidad.
- Costo por superficie.
- Plus de electricidad cuando corresponda.

Ejemplo probado:

- Superficie: `25 m²`
- Costo por superficie: `$5.000`
- Plus por electricidad: `$15.000`
- Canon obtenido: `$140.000,00`

#### PuestoDesarmable

El canon se calcula considerando:

- Superficie de la Unidad.
- Costo por superficie.
- Minutos de montaje.
- Costo por montaje.

#### Gestión de costos

Se agregó la posibilidad de configurar y modificar los costos correspondientes a cada Festival.

Los costos incluyen:

- Costo por superficie.
- Costo por montaje.
- Plus por electricidad.
- Sueldo base.
- Valor por año de antigüedad.
- Plus Cocinero.
- Plus Ayudante.
- Plus Lavaplatos.

También se implementaron:

- Validaciones para evitar costos negativos.
- Validación de campos obligatorios.
- Manejo de Festivales sin costos configurados.
- Formato monetario para los importes.
- Navegación desde el panel de Administración.
- Pantalla específica de Costos de Festivales.
- Pantalla de detalle del canon.

---

### CU - Reporte de Mayores Cánones

Responsable: **Valentina Ponzo**

Permite al Administrador consultar las Unidades de Venta de un Festival ordenadas según el valor de su canon, desde el canon más alto hasta el más bajo.

Para generar el reporte se utiliza:

- `ReporteMayoresCanonDTO`
- `ReporteCanonService`
- La lógica de cálculo de canon ya implementada para cada tipo de Unidad de Venta.

El reporte muestra:

- Posición dentro del ranking.
- Nombre de la Unidad de Venta.
- Código.
- Tipo de Unidad.
- Canon calculado.
- Acceso al detalle individual del canon.

El Administrador selecciona el Festival sobre el cual desea generar el reporte.

#### Festival sin costos configurados

Si el Festival no posee costos configurados:

- No se intenta calcular el reporte.
- Se informa que los costos todavía no están configurados.
- Se ofrece acceso directo a la configuración de costos.

#### Festival con costos configurados

Si el Festival ya posee costos:

- Se permite modificarlos.
- Se habilita la generación del reporte.
- Se calcula el canon de cada Unidad de Venta.
- Los resultados se ordenan de mayor a menor.

#### Prueba realizada

Festival: `Primavera Gourmet`

Resultado obtenido:

1. `Puesto Centro` - Puesto Desarmable - `$148.000,00`
2. `Burger Point` - Food Truck - `$140.000,00`

El resultado obtenido corresponde al orden esperado de mayor a menor.

---

### CU - Alta y Listado de Empleados

Responsable: **Alejandro Escalier**

Permite al Responsable registrar nuevos empleados y consultar los empleados de su Unidad de Venta.

#### Rutas

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/responsable/empleados` | Listado de empleados |
| `GET` | `/responsable/empleados/nuevo` | Formulario de alta |
| `POST` | `/responsable/empleados/nuevo` | Registro del empleado |

#### Alta de empleado

Tipos de empleado soportados:

- `Cocinero`, con rol de cocina: `COCINERO`, `AYUDANTE` o `LAVAPLATOS`.
- `Cajero`, con turno: `MANANA` o `NOCHE`.

Durante el registro se crea:

- El empleado.
- El usuario con rol `EMPLEADO`.
- La contraseña cifrada con BCrypt.
- La asignación del empleado a la Unidad de Venta del Responsable.

Validaciones:

- Nombre y apellido obligatorios.
- DNI mayor a cero y no repetido.
- Email válido y no repetido.
- Empleado mayor de 18 años.
- Fecha de ingreso no futura.
- Tipo de empleado obligatorio.
- Rol de cocina obligatorio para `Cocinero`.
- Turno obligatorio para `Cajero`.
- Username único.
- Contraseña de al menos 6 caracteres.

---

### CU - Reactivación de Empleados

Responsable: **Alejandro Escalier**

Permite volver a dar de alta a un empleado.

Funcionalidades:

- Reactivación del empleado.
- Reactivación del usuario.
- Restablecimiento del acceso.
- Confirmación antes de la operación.
- Mensajes de éxito y error.
- Control por Unidad de Venta.
- Operación transaccional.

---

### CU - Liquidación de Sueldos

Responsable: **Valentina Ponzo**

Permite al Responsable liquidar los haberes de los empleados asignados a su Unidad de Venta.

Funcionalidades:

- Obtención de la Unidad del Responsable autenticado.
- Consulta de empleados de su propia Unidad.
- Exclusión del Responsable.
- Cálculo mediante polimorfismo para `Cocinero` y `Cajero`.
- Uso de los costos del Festival.
- Persistencia del sueldo en `AsignacionEmpleado.sueldoLiquidado`.
- Uso de `LiquidacionSueldoDTO`.
- Validación de Festival y costos.
- Protección entre Unidades.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Tipo: `Cocinero`
- Categoría: `AYUDANTE`
- Festival: `Primavera Gourmet`
- Sueldo liquidado: `$440.000,00`

El importe fue persistido correctamente en MySQL.

---

### CU - Panel de inicio del Empleado

Responsable: **Alejandro Escalier**

Pantalla principal que ve el Empleado al iniciar sesión.

Ruta:

`GET /empleado`

Muestra:

- Unidad de Venta.
- Festival.
- Puesto.
- Datos personales.
- Unidad asignada.
- Accesos a las funcionalidades disponibles.

El panel fue posteriormente integrado con:

- Festival y Unidad actual.
- Historial laboral.
- Mis sueldos.
- Alta de Pedido.

---

### CU - Consultar sueldo por Festival

Responsable: **Valentina Ponzo**

Permite que el Empleado autenticado consulte sus haberes por Festival.

Funcionalidades:

- Identificación del empleado autenticado.
- Consulta de asignaciones laborales.
- Festival y temporada.
- Unidad de Venta.
- Fechas.
- Sueldo liquidado.
- Manejo de sueldos pendientes.
- Uso de `SueldoEmpleadoDTO`.
- Acceso desde Panel y sidebar.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Festival: `Primavera Gourmet`
- Temporada: `Primavera 2026`
- Unidad: `Puesto Centro`
- Sueldo: `$440.000,00`

---

### CU - Consultar Festival y Unidad actual

Responsable: **Valentina Ponzo**

Permite consultar la asignación laboral actual del empleado.

Muestra:

- Unidad de Venta.
- Código.
- Tipo.
- Superficie.
- Responsable.
- Festival.
- Temporada.
- Fechas.
- Tipo de empleado.
- Categoría o turno.

Utiliza `EmpleadoActualDTO`.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Unidad: `Puesto Centro`
- Código: `PUEST00002`
- Tipo: `Puesto Desarmable`
- Festival: `Primavera Gourmet`
- Puesto: `Cocinero - AYUDANTE`

---

### CU - Consultar historial laboral

Responsable: **Valentina Ponzo**

Permite consultar todas las asignaciones laborales del empleado autenticado.

Funcionalidades:

- Consulta de `AsignacionEmpleado`.
- Ordenamiento por fecha.
- Festival.
- Temporada.
- Unidad.
- Código.
- Tipo de Unidad.
- Fecha desde.
- Fecha hasta.
- Estado actual o finalizado.
- Uso de `HistorialLaboralDTO`.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Festival: `Primavera Gourmet`
- Unidad: `Puesto Centro`
- Desde: `2026-10-19`
- Hasta: `Actual`
- Estado: `Actual`

---

### CU - Consulta de Empleados con filtros

Responsable: **Alejandro Escalier**

Permite al Responsable buscar empleados de su Unidad por:

- Nombre o apellido.
- DNI.
- Fecha de ingreso desde.
- Fecha de ingreso hasta.

Los filtros pueden combinarse.

Funcionalidades:

- Búsqueda parcial.
- Búsqueda sin distinguir mayúsculas ni tildes.
- Rango de fechas.
- Conservación de filtros.
- Resultados únicamente de la Unidad del Responsable.
- El Responsable no aparece en los resultados.
- Validación del rango de fechas.

---

### CU - Agregar Platos a Unidad de Venta

Responsable: **Cristian Medina**

Permite registrar platos asociados a una Unidad de Venta.

Los platos contienen:

- Nombre.
- Precio de venta.
- Costo de producción.
- Unidad de Venta.

Los platos registrados son posteriormente utilizados en:

- Pedidos.
- Recaudación.
- Rentabilidad.
- Plato Estrella.

---

### CU - Envío de mail en alta de usuario

Responsable: **Cristian Medina**

Permite enviar las credenciales de acceso al empleado al momento de registrar su usuario.

Funcionalidad integrada al alta de empleado.

El email permite comunicar:

- Usuario.
- Credenciales de acceso.
- Información necesaria para ingresar al sistema.

Este Caso de Uso permite cumplir con el requisito general de contar con al menos una prueba de envío de email.

---

### CU - Alta de Pedido

Responsable: **Valentina Ponzo**

Permite que un Empleado registre una nueva venta correspondiente a su Unidad de Venta.

#### Rutas

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/empleado/pedidos/nuevo` | Formulario de Alta de Pedido |
| `POST` | `/empleado/pedidos/nuevo` | Registro del Pedido |

Funcionalidades implementadas:

- Obtención automática de la Unidad de Venta del empleado autenticado.
- Obtención automática del Festival asociado a la Unidad.
- Fecha del pedido establecida automáticamente con la fecha actual.
- Listado únicamente de los platos pertenecientes a la Unidad del empleado.
- Selección de plato.
- Registro de cantidad.
- Validación de plato obligatorio.
- Validación de cantidad mayor a cero.
- Validación de que el plato pertenezca a la Unidad del empleado.
- Creación de `Pedido`.
- Creación de `ItemPedido`.
- Persistencia del Pedido y sus ítems mediante `CascadeType.ALL`.
- Uso de `RegistroPedidoDTO`.
- Mensajes de éxito y error.
- Confirmación antes de registrar.
- Integración con el Panel del Empleado.
- Acceso desde el menú lateral.

#### Prueba realizada

Se registró correctamente:

- Empleado autenticado: `Lucía Fernández`
- Unidad: `Puesto Centro`
- Festival: `Primavera Gourmet`
- Plato: `Hamburguesa Completa`
- Precio de venta: `$12.000,00`
- Pedido generado: `#1`

Se verificó en MySQL:

- Registro creado en `pedido`.
- Registro relacionado creado en `item_pedido`.
- `id_pedido = 1`.
- `id_plato = 1`.
- Cantidad registrada correctamente.

Pull Request: [PR #30](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/30)

---

### CU - Envío de mail en reactivación de empleado
 
Responsable: **Alejandro Escalier**
 
Permite notificar por email al empleado cada vez que el Responsable reactiva su cuenta.
 
Funcionalidad integrada a la Reactivación de Empleados.
 
#### Ruta
 
| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/responsable/empleados/{id}/reactivar` | Reactivación del empleado y envío del mail |
 
Funcionalidades:
 
- Envío automático del mail al reactivar un empleado.
- Plantilla HTML propia: `mail-reactivacion-empleado.html`.
- Logo de Epicentro Gourmet incrustado en el mail.
- Método `enviarMailReactivacion` agregado a `IMailService` e implementado en `MailService`.
- Reutilización de la configuración SMTP existente.

---

### CU - Listado de Empleados en PDF

Responsable: **Alejandro Escalier**

Permite al Responsable descargar en formato PDF el listado de empleados de su Unidad de Venta.

#### Ruta

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/responsable/empleados/pdf` | Descarga del listado de empleados en PDF |

Funcionalidades:

- Botón "Descargar PDF" en el listado de empleados, visible solo para el Responsable.
- Generación del PDF mediante la librería OpenPDF.
- Servicio propio: `IPdfService` implementado en `PdfService`.
- Título "Epicentro Gourmet - Listado de empleados".
- Tabla con las columnas: Nombre, Puesto, DNI, Email, Ingreso y Estado.
- Puesto diferenciado por tipo de empleado: `Cocinero` (con su rol de cocina) o `Cajero` (con su turno).
- Estado del empleado: `Activo` o `Baja`.
- Cabecera de la tabla repetida en cada página.
- Descarga como archivo adjunto: `listado-empleados.pdf`.
- Manejo de errores en la generación del PDF (respuesta `400`).

---

### Navegación y redirección según rol

Flujo actual:

- `RESPONSABLE` → `/responsable`
- `EMPLEADO` → `/empleado`
- Administrador → `/`

Existen menús laterales específicos para:

- Administrador.
- Responsable.
- Empleado.

El Panel del Empleado integra actualmente:

- Festival y Unidad actual.
- Historial laboral.
- Mis sueldos.
- Alta de Pedido.

---

## Roles del sistema

### Administrador

Funcionalidades actuales:

- Consultar Festivales.
- Consultar Unidades de Venta.
- Registrar Unidad de Venta y Responsable.
- Configurar costos.
- Consultar cánones.
- Reporte de mayores cánones.

Funcionalidades pendientes:

- Ranking de facturación.

---

### Responsable

Funcionalidades actuales:

- Consultar su Unidad.
- Consultar canon.
- Panel específico.
- Alta de empleados.
- Listado de empleados.
- Modificación.
- Baja lógica.
- Reactivación.
- Filtros y búsqueda.
- Liquidación de haberes.
- Agregar platos.
- Envío de credenciales por email.

Funcionalidades pendientes:

- Plato Estrella.
- Reporte de ventas.
- Rentabilidad neta.
- Rentabilidad entre fechas.

---

### Empleado

Funcionalidades actuales:

- Panel del Empleado.
- Festival y Unidad actual.
- Historial laboral.
- Consulta de sueldo por Festival.
- Alta de Pedido.

Funcionalidades pendientes:

- Descargar recibo de sueldo en PDF.

Los empleados de tipo `Cajero` podrán además realizar el cierre diario de caja.

---

## Interfaz y navegación

Se implementó:

- Barra superior.
- Menús laterales.
- Dashboard de Administración.
- Panel del Responsable.
- Panel del Empleado.
- Navegación por roles.
- Listado de Festivales.
- Gestión de Unidades.
- Gestión de empleados.
- Filtros de empleados.
- Costos.
- Canon.
- Liquidación de Sueldos.
- Consulta de sueldo.
- Festival y Unidad actual.
- Historial laboral.
- Alta de Pedido.
- Reporte de Mayores Cánones.
- Diseño reutilizable mediante CSS.
- Fragmentos Thymeleaf.

---

## Casos de Uso en desarrollo / pendientes

Entre las funcionalidades restantes se encuentran:

- Consultar Plato Estrella.
- Generar recibo de sueldo en PDF.
- Generar reporte de ventas.
- Calcular rentabilidad neta.
- Calcular rentabilidad entre fechas.
- Ranking de facturación.
- Cierre de caja del Cajero.

> Esta sección deberá continuar actualizándose a medida que se integren nuevos Pull Requests.

---

## Requisitos generales pendientes de integración

Estado actual:

- Envío de email: **implementado**.
- Reporte mediante DTO: **implementado** con `ReporteMayoresCanonDTO`.
- Generación de PDF: **pendiente**.
- Dos Casos de Uso por integrante: **verificar al cierre**.
- Dos validaciones por integrante: **verificar al cierre**.
- Script final de datos de prueba: **pendiente**.
- Pruebas integrales: **pendientes de cierre**.

DTOs utilizados actualmente:

- `ReporteMayoresCanonDTO`
- `LiquidacionSueldoDTO`
- `SueldoEmpleadoDTO`
- `EmpleadoActualDTO`
- `HistorialLaboralDTO`
- `RegistroPedidoDTO`

---

## Metodología Git

Para cada Caso de Uso:

1. Se crea una rama específica desde `main`.
2. Se desarrolla y prueba.
3. Se realiza un commit descriptivo.
4. Se publica la rama.
5. Se crea un Pull Request.
6. Se verifican conflictos.
7. Se documenta el PR en el README.
8. Se realiza el merge hacia `main`.

---

## Base de datos

La aplicación utiliza MySQL.

Variables de entorno:

```bash
export DB_USER='usuario_mysql'
export DB_PASSWORD='password_mysql'
```

También puede configurarse la URL:

```bash
export DB_URL='jdbc:mysql://localhost:3308/epicentrogourmet?createDatabaseIfNotExist=true&serverTimezone=America/Argentina/Buenos_Aires'
```

El puerto puede variar según la configuración local de cada integrante.

Administrador:

```bash
export ADMIN_PASSWORD='password_admin'
```

Opcional:

```bash
export ADMIN_USERNAME='admin'
```

---

## Ejecución del proyecto

Ingresar a la aplicación:

```bash
cd epicentrogourmet
```

Compilar:

```bash
./mvnw -DskipTests compile
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

Acceder:

```text
http://localhost:8080/
```

---

## Estado actual

Actualmente el proyecto cuenta con:

- Modelo de dominio persistido mediante JPA/Hibernate.
- MySQL.
- Spring Security.
- Roles `ADMINISTRADOR`, `RESPONSABLE` y `EMPLEADO`.
- BCrypt.
- Dashboard de Administración.
- Panel del Responsable.
- Panel del Empleado.
- Redirección según rol.
- Listado de Festivales.
- Registro de Unidad + Responsable.
- Consulta de Unidades por Festival.
- Configuración y modificación de costos.
- Cálculo de canon.
- Reporte de Mayores Cánones.
- Alta y listado de empleados.
- Modificación, baja y reactivación.
- Filtros de empleados.
- Gestión de empleados por Unidad.
- Alta de platos.
- Envío de credenciales por email.
- Asignación automática de empleados.
- Liquidación de sueldos.
- Persistencia de haberes.
- Consulta de sueldo por Festival.
- Consulta de Festival y Unidad actual.
- Historial laboral.
- Alta de Pedido.
- Persistencia de `Pedido` e `ItemPedido`.
- Integración del Alta de Pedido al Panel del Empleado.
- Menús laterales por rol.
- Flujo mediante ramas y Pull Requests.
