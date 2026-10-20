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

| Caso de Uso | Responsable | Estado | Rama | Pull Request |
|---|---|---|---|---|
| Inicio de sesión | Valentina Ponzo | Finalizado | `CU-Login` | [PR #5](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/5) |
| Listado de Festivales | Valentina Ponzo | Finalizado | `CU-Listado-Festivales` | [PR #6](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/6) |
| Registrar Unidad de Venta + Responsable | Valentina Ponzo | Finalizado | `CU-Registrar-Unidad-Responsable` | [PR #7](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/7) |
| Consultar Unidades por Festival | Valentina Ponzo | Finalizado | `CU-Unidades-Por-Festival` | [PR #8](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/8) |
| Calcular Canon de Unidad de Venta | Valentina Ponzo | Finalizado | `CU-Calcular-Canon` | [PR #10](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/10) |
| Alta y Listado de Empleados | Alejandro Escalier | Finalizado | `CU-Alta-Listado-Empleados` | [PR #11](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/11) |
| Reactivación de Empleados | Alejandro Escalier | Finalizado | `CU-Reactivacion-Empleados` | [PR #15](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/15) |
| Reporte de Mayores Cánones | Valentina Ponzo | Finalizado | `CU-Reporte-Mayores-Canones` | [PR #16](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/16) |
| Liquidación de Sueldos | Valentina Ponzo | Finalizado | `CU-Liquidacion-Sueldos` | [PR #18](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/18) |
| Panel de inicio del Empleado | Alejandro Escalier | Finalizado | `CU-Dashboard-Empleado` | [PR #20](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/20) |
| Consultar sueldo por Festival | Valentina Ponzo | Finalizado | `CU-Consultar-Sueldo-Festival` | [PR #21](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/21) |
| Consultar Festival y Unidad actual | Valentina Ponzo | Finalizado | `CU-Festival-Unidad-Actual-Empleado` | [PR #22](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/22) |
| Consultar historial laboral | Valentina Ponzo | Finalizado | `CU-Historial-Laboral-Empleado` | [PR #23](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/23) |
| Consulta de Empleados con filtros | Alejandro Escalier | Finalizado | `CU-Consulta-Empleados` | [PR #26] (https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/26)|

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

El reporte se encuentra integrado con:

- Dashboard del Administrador.
- Menú lateral del Administrador.
- Accesos rápidos.
- Selección de Festival.
- Configuración y modificación de costos.
- Pantalla de detalle del ranking.
- Detalle individual del canon.

---

### CU - Alta y Listado de Empleados

Responsable: **Alejandro Escalier**

Permite al Responsable registrar nuevos empleados y consultar los empleados de su Unidad de Venta.

**Rutas:**

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/responsable/empleados` | Listado de empleados |
| `GET` | `/responsable/empleados/nuevo` | Formulario de alta |
| `POST` | `/responsable/empleados/nuevo` | Registro del empleado |

#### Alta de empleado

Tipos de empleado soportados:

- `Cocinero`, con rol de cocina: `COCINERO`, `AYUDANTE` o `LAVAPLATOS`.
- `Cajero`, con turno: `MANANA` o `NOCHE`.

Durante el registro, en una única operación transaccional, se crea:

- El empleado (`Cocinero` o `Cajero`).
- El usuario de acceso con rol `EMPLEADO`, vinculado al empleado.
- La contraseña cifrada con BCrypt.
- La asignación (`AsignacionEmpleado`) del empleado a la Unidad de Venta del Responsable que lo registra.

Validaciones implementadas:

- Nombre y apellido obligatorios.
- DNI obligatorio, mayor a cero y no repetido.
- Email obligatorio, con formato válido y no repetido.
- Fecha de nacimiento obligatoria y anterior a la fecha actual.
- Empleado mayor de 18 años.
- Fecha de ingreso obligatoria y no futura.
- Tipo de empleado obligatorio.
- Rol de cocina obligatorio para `Cocinero`.
- Turno obligatorio para `Cajero`.
- Nombre de usuario obligatorio y único.
- Contraseña obligatoria de al menos 6 caracteres.
- El Responsable debe tener una Unidad de Venta con Festival asociado.
- Mensajes de error por campo y mensaje general ante datos duplicados.
- Mensaje de confirmación al registrar correctamente.

#### Listado de empleados

- Tabla con los empleados asignados a la Unidad de Venta del Responsable autenticado.
- El Responsable no aparece en su propio listado.
- Empleados ordenados por DNI.
- Datos visibles: nombre y apellido, puesto, DNI, email, fecha de ingreso y estado.
- Contador de empleados registrados.
- Botón de acceso rápido a Nuevo Empleado.
- Manejo del listado vacío.
- Visualización de la Unidad de Venta del Responsable.
- Acceso al canon de su Unidad de Venta.

---

### CU - Reactivación de Empleados

Responsable: **Alejandro Escalier**

Permite volver a dar de alta a un empleado de la base de datos.

**Rutas:**

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/responsable/empleados/{id}/reactivar` | Reactivación |

Funcionalidades:

- El empleado vuelve a quedar activo.
- Su usuario vuelve a quedar activo.
- Se restablece el acceso al sistema.
- Los botones disponibles dependen del estado del empleado.
- Se solicita confirmación antes de realizar la acción.
- Se muestran mensajes de éxito o error.
- Solo se pueden reactivar empleados de la Unidad de Venta del Responsable.
- La operación se realiza de forma transaccional.

---

### CU - Liquidación de Sueldos

Responsable: **Valentina Ponzo**

Permite al Responsable liquidar los haberes de los empleados asignados a su Unidad de Venta.

Funcionalidades implementadas:

- Obtención de la Unidad de Venta del Responsable autenticado.
- Consulta únicamente de empleados pertenecientes a esa Unidad.
- Asignación automática de nuevos empleados a la Unidad del Responsable.
- Exclusión del propio Responsable de la liquidación.
- Cálculo de haberes mediante polimorfismo para `Cocinero` y `Cajero`.
- Uso de los costos configurados para el Festival.
- Persistencia del sueldo en `AsignacionEmpleado.sueldoLiquidado`.
- Uso de `LiquidacionSueldoDTO` para mostrar los resultados.
- Validación de Festival y costos configurados.
- Protección para impedir que un Responsable gestione empleados de otras Unidades.
- Incorporación de un Panel principal para el rol Responsable.

#### Prueba realizada

Se registró y asignó un empleado a `Puesto Centro`:

- Empleado: `Lucía Fernández`
- Tipo: `Cocinero`
- Categoría: `AYUDANTE`
- Festival: `Primavera Gourmet`
- Sueldo liquidado: `$440.000,00`

El importe fue calculado correctamente y persistido en MySQL.

---

### CU - Panel de inicio del Empleado

Responsable: **Alejandro Escalier**

Pantalla principal que ve el Empleado al iniciar sesión, con la misma estructura general que el Panel del Responsable.

**Ruta:**

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/empleado` | Panel de inicio del Empleado (`empleado-dashboard.html`) |

Información que se muestra:

- **Tarjetas de resumen:** Unidad de Venta, Festival y puesto.
- **Mis datos:** nombre y apellido, DNI, email, fecha de ingreso y estado.
- **Mi Unidad de Venta:** nombre comercial, tipo, código, Festival y Responsable.
- **Mi actividad:** accesos a las funcionalidades disponibles para el Empleado.
- Si el empleado no tiene una Unidad de Venta asignada, se muestra un aviso.

Implementación:

- `HomeController.empleadoInicio`: arma el modelo con el usuario autenticado, su empleado, su Unidad y su Festival.
- `UnidadVentaService.buscarUnidadDelEmpleado`: obtiene la Unidad vigente del empleado a partir de su asignación.
- `AsignacionEmpleadoRepository`: permite consultar asignaciones del empleado.

El panel fue posteriormente integrado con las funcionalidades de Festival y Unidad actual, Historial laboral y Mis sueldos.

---

### CU - Consultar sueldo por Festival

Responsable: **Valentina Ponzo**

Permite que el Empleado autenticado consulte los haberes recibidos en cada Festival.

Funcionalidades implementadas:

- Identificación del empleado autenticado.
- Consulta de sus asignaciones laborales.
- Visualización del Festival.
- Visualización de temporada.
- Visualización de Unidad de Venta.
- Visualización de fechas del Festival.
- Visualización del sueldo liquidado.
- Manejo de sueldos pendientes de liquidación.
- Uso de `SueldoEmpleadoDTO`.
- Acceso desde el Panel del Empleado.
- Acceso desde el menú lateral.
- Navegación de regreso al Panel del Empleado.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Festival: `Primavera Gourmet`
- Temporada: `Primavera 2026`
- Unidad: `Puesto Centro`
- Sueldo recibido: `$440.000,00`

La información coincide con el sueldo previamente liquidado y persistido en MySQL.

---

### CU - Consultar Festival y Unidad actual

Responsable: **Valentina Ponzo**

Permite que el Empleado autenticado consulte su asignación laboral actual.

Funcionalidades implementadas:

- Obtención del empleado autenticado.
- Consulta de la Unidad de Venta asignada.
- Visualización del nombre comercial de la Unidad.
- Visualización del código.
- Visualización del tipo de Unidad.
- Visualización de la superficie.
- Visualización del Responsable.
- Consulta del Festival asociado.
- Visualización del nombre y temporada del Festival.
- Visualización de las fechas de inicio y fin.
- Visualización del tipo de empleado.
- Visualización de categoría o turno.
- Uso de `EmpleadoActualDTO`.
- Acceso desde el menú lateral.
- Navegación de regreso al Panel del Empleado.

#### Prueba realizada

- Empleado: `Lucía Fernández`
- Unidad: `Puesto Centro`
- Código: `PUEST00002`
- Tipo de Unidad: `Puesto Desarmable`
- Festival: `Primavera Gourmet`
- Temporada: `Primavera 2026`
- Puesto: `Cocinero - AYUDANTE`

---

### CU - Consultar historial laboral

Responsable: **Valentina Ponzo**

Permite al Empleado autenticado consultar todas sus asignaciones laborales registradas.

Funcionalidades implementadas:

- Consulta de todas las `AsignacionEmpleado` correspondientes al empleado autenticado.
- Ordenamiento de las asignaciones por fecha.
- Visualización del Festival.
- Visualización de temporada.
- Visualización de Unidad de Venta.
- Visualización del código de la Unidad.
- Visualización del tipo de Unidad.
- Visualización de fecha desde.
- Visualización de fecha hasta.
- Diferenciación entre asignaciones actuales y finalizadas.
- Uso de `HistorialLaboralDTO`.
- Acceso desde el Panel del Empleado.
- Acceso desde el menú lateral.
- Navegación de regreso al Panel del Empleado.

#### Prueba realizada

Para `Lucía Fernández` se visualizó correctamente:

- Festival: `Primavera Gourmet`
- Temporada: `Primavera 2026`
- Unidad: `Puesto Centro`
- Tipo: `Puesto Desarmable`
- Desde: `2026-10-19`
- Hasta: `Actual`
- Estado: `Actual`

---

### CU - Consulta de Empleados con filtros
 
Responsable: **Alejandro Escalier**
 
Permite al Responsable buscar empleados de su Unidad de Venta por nombre, DNI o fecha de ingreso. El panel de filtros reemplaza al panel de Unidad de Venta en la vista `empleados.html`.
 
**Rutas:**
 
| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/responsable/empleados` | Listado de empleados, con filtros opcionales por parámetros |
 
**Parámetros (todos opcionales y combinables):**
 
| Parámetro | Tipo | Descripción |
|---|---|---|
| `nombre` | Texto | Nombre o apellido, búsqueda parcial |
| `dni` | Texto numérico | DNI, búsqueda parcial |
| `fechaDesde` | Fecha (`yyyy-MM-dd`) | Fecha de ingreso desde, inclusive |
| `fechaHasta` | Fecha (`yyyy-MM-dd`) | Fecha de ingreso hasta, inclusive |
 
Funcionalidades:
 
- Panel "Consultar empleados" con campos de nombre o apellido, DNI e ingreso desde/hasta.
- Botón **Buscar** y botón **Limpiar filtros**.
- Los filtros se combinan entre sí (se aplican todos los que estén completos).
- Búsqueda de nombre sin distinguir mayúsculas ni tildes (`perez` encuentra `Pérez`).
- Búsqueda de nombre sobre nombre y apellido juntos (`juan perez`).
- Búsqueda de DNI por coincidencia parcial (`4012` encuentra `40123456`).
- Filtro por rango de fecha de ingreso; se puede completar solo una de las dos fechas.
- Los valores ingresados se mantienen cargados luego de buscar.
- La consulta se hace por `GET`, por lo que puede recargarse o guardarse como favorito.
- El título de la tabla cambia a "Resultados de la búsqueda" cuando hay filtros aplicados.
- Mensaje específico cuando ningún empleado coincide con los filtros.
- Con filtros activos se oculta el botón "Registrar Empleado" del estado vacío.
- Los resultados mantienen las acciones de Modificar, Dar de baja y Dar de alta.
Validaciones:
 
- Si la fecha "desde" es posterior a la fecha "hasta", se informa el error y se ignoran ambas fechas.
- El filtrado se realiza solo sobre empleados de la Unidad de Venta del Responsable autenticado; no se pueden consultar empleados de otras Unidades.
- El Responsable no aparece en los resultados.
Implementación:
 
- `EmpleadoController.empleadoInicio`: recibe los parámetros con `@RequestParam` y `@DateTimeFormat`, y devuelve a la vista los valores del filtro (`filtroNombre`, `filtroDni`, `filtroDesde`, `filtroHasta`) y el indicador `hayFiltros`.
- `EmpleadoService.buscarEmpleados`: filtra sobre `listarEmpleados()`, que ya limita los empleados a la Unidad del Responsable.
- `EmpleadoService.normalizar`: normaliza el texto (sin tildes y en minúsculas) para comparar nombres.
- `empleados.html`: se reemplazó el panel de Unidad de Venta por el formulario de consulta.

### Navegación y redirección según rol

Flujo actual:

- Tras iniciar sesión, el `RESPONSABLE` es redirigido a `/responsable`.
- Tras iniciar sesión, el `EMPLEADO` es redirigido a `/empleado`.
- Los demás roles continúan en `/`.
- Si un Responsable o un Empleado ingresa a `/`, también es redirigido a su panel.
- Existe menú lateral específico para Responsable.
- Existe menú lateral específico para Empleado.
- El Panel del Empleado integra accesos a Festival y Unidad actual, Historial laboral y Mis sueldos.

---

## Roles del sistema

La aplicación contempla tres tipos principales de usuario.

### Administrador

Funcionalidades actuales:

- Consultar Festivales.
- Consultar Unidades de Venta.
- Registrar Unidad de Venta y Responsable.
- Configurar costos de Festivales.
- Consultar cánones.
- Consultar reporte de mayores cánones.

Funcionalidades previstas:

- Consultar ranking de facturación.

### Responsable

Administra la Unidad de Venta que tiene asignada.

Funcionalidades actuales:

- Consultar su Unidad de Venta y su canon.
- Acceder a un Panel principal específico.
- Alta y listado de empleados.
- Modificación de empleados.
- Baja lógica de empleados.
- Reactivación de empleados.
- Gestionar empleados pertenecientes a su Unidad.
- Liquidar haberes de los empleados de su Unidad.

Funcionalidades previstas:

- Filtros y búsqueda de empleados.
- Agregar platos.
- Consultar Plato Estrella.
- Consultar reportes de ventas.
- Consultar rentabilidad.
- Enviar credenciales a empleados.

### Empleado

Consulta información relacionada con su actividad laboral.

Funcionalidades actuales:

- Acceder al Panel del Empleado.
- Consultar Festival y Unidad actual.
- Consultar historial laboral.
- Consultar sueldo recibido por Festival.

Funcionalidades previstas:

- Descargar recibo de sueldo en PDF.

Los empleados de tipo `Cajero` podrán además realizar el cierre diario de caja.

---

## Interfaz y navegación

Se implementó una interfaz web con:

- Barra superior.
- Menú lateral.
- Dashboard de Administración.
- Panel principal del Responsable.
- Panel principal del Empleado.
- Accesos rápidos.
- Navegación entre Festivales, Unidades, Costos y Canon.
- Gestión de empleados por Unidad de Venta.
- Liquidación de Sueldos.
- Consulta de sueldo por Festival.
- Consulta de Festival y Unidad actual.
- Consulta de historial laboral.
- Reporte de Mayores Cánones.
- Diseño reutilizable mediante CSS.
- Fragmentos Thymeleaf para los menús laterales.

Actualmente existen estructuras de navegación para:

- Administrador.
- Responsable.
- Empleado.

---

## Casos de Uso en desarrollo / pendientes

Entre las funcionalidades restantes se encuentran:

- Filtros y búsqueda de empleados.
- Agregar platos a una Unidad de Venta.
- Consultar Plato Estrella.
- Enviar credenciales mediante email.
- Generar recibo de sueldo en PDF.
- Generar reporte de ventas.
- Calcular rentabilidad neta.
- Calcular rentabilidad entre fechas.
- Ranking de facturación.
- Cierre de caja del Cajero.

> Esta sección deberá continuar actualizándose a medida que se integren nuevos Pull Requests a `main`.

---

## Requisitos generales pendientes de integración

Estado de requisitos generales:

- Envío de email: pendiente de integración.
- Reporte implementado mediante DTO: implementado mediante `ReporteMayoresCanonDTO`.
- Generación de archivo PDF: pendiente de integración.
- Dos Casos de Uso por integrante: verificar al cierre.
- Dos validaciones por integrante: verificar al cierre.
- Script final con datos suficientes para la demostración: pendiente.
- Pruebas integrales de los distintos roles: pendientes de cierre.

Actualmente el proyecto incorpora DTOs específicos para presentación y reportes, entre ellos:

- `ReporteMayoresCanonDTO`
- `LiquidacionSueldoDTO`
- `SueldoEmpleadoDTO`
- `EmpleadoActualDTO`
- `HistorialLaboralDTO`

---

## Metodología Git

Para cada Caso de Uso:

1. Se crea una rama específica desde `main`.
2. Se desarrolla y prueba la funcionalidad.
3. Se realiza un commit descriptivo.
4. Se publica la rama en GitHub.
5. Se crea un Pull Request hacia `main`.
6. Se verifica que no existan conflictos.
7. Se agrega el link del Pull Request correspondiente al README.
8. Se realiza el merge hacia `main`.

---

## Base de datos

La aplicación utiliza MySQL.

La conexión se configura mediante variables de entorno.

Ejemplo:

```bash
export DB_USER='usuario_mysql'
export DB_PASSWORD='password_mysql'
```

También puede configurarse la URL completa de conexión:

```bash
export DB_URL='jdbc:mysql://localhost:3308/epicentrogourmet?createDatabaseIfNotExist=true&serverTimezone=America/Argentina/Buenos_Aires'
```

El puerto puede variar según la configuración local de cada integrante.

Para el usuario administrador inicial:

```bash
export ADMIN_PASSWORD='password_admin'
```

Opcionalmente puede definirse:

```bash
export ADMIN_USERNAME='admin'
```

---

## Ejecución del proyecto

Ingresar a la carpeta de la aplicación:

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

Luego acceder desde el navegador a:

```text
http://localhost:8080/
```

---

## Estado actual

Actualmente el proyecto cuenta con:

- Modelo de dominio persistido mediante JPA/Hibernate.
- Conexión con MySQL.
- Login con Spring Security.
- Roles `ADMINISTRADOR`, `RESPONSABLE` y `EMPLEADO`.
- Contraseñas cifradas mediante BCrypt.
- Dashboard de Administración.
- Panel principal del Responsable.
- Panel principal del Empleado.
- Redirección al panel correspondiente según rol.
- Navegación mediante Thymeleaf.
- Listado de Festivales.
- Registro de Unidad de Venta + Responsable.
- Consulta de Unidades por Festival.
- Configuración y modificación de costos.
- Cálculo de canon.
- Reporte de Mayores Cánones mediante `ReporteMayoresCanonDTO`.
- Ranking de cánones de mayor a menor.
- Alta y listado de empleados por Unidad de Venta.
- Modificación, baja lógica y reactivación de empleados.
- Gestión de empleados filtrada por Unidad de Venta.
- Asignación automática de empleados a la Unidad del Responsable.
- Liquidación de sueldos.
- Persistencia de haberes liquidados.
- Consulta de sueldo por Festival.
- Consulta de Festival y Unidad actual del Empleado.
- Consulta de historial laboral del Empleado.
- Estructura de navegación del Responsable.
- Estructura de navegación del Empleado.
- Flujo de desarrollo mediante ramas y Pull Requests.
