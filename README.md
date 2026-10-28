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

Consulta empleados y determina su tipo concreto dentro de la jerarquía de empleados.

Relación de **herencia** entre `Empleado` y sus subtipos.

#### Consultar Unidad de Venta por código

Consulta una Unidad de Venta por su código y determina si corresponde a un `FoodTruck` o un `PuestoDesarmable`.

Relación de **herencia** entre `UnidadVenta`, `FoodTruck` y `PuestoDesarmable`.

---

### José Arratia

#### Traer Festival por fecha

Se agrega un Festival de prueba y luego se lo consulta mediante su fecha de inicio.

---

### Cristian Prantera

#### Actualizar Plato

Consulta un Plato existente por su ID, modifica su precio de venta y actualiza el registro mediante Hibernate.

#### Consultar Platos por precio mayor a

Consulta y filtra una lista de Platos cuyo precio de venta sea superior a un monto determinado.

Relación: **Uno a Muchos (1:N)** entre `UnidadVenta` y `Plato`.

---

### Cristian Medina

#### Consultar Pedidos filtrados por Festival y fecha

Consulta pedidos correspondientes a un Festival mediante su ID y la fecha del pedido.

Relación: **Uno a Muchos (1:N)** entre `Festival` y `Pedido`.

#### Consultar Platos por Festival

Consulta los Platos correspondientes a un Festival.

---

### Alejandro Escalier

#### Consultar Empleados de Unidad de Venta

Consulta el listado de empleados correspondientes a una Unidad de Venta.

#### Consultar Platos de Unidad de Venta

Consulta el listado de Platos correspondientes a una Unidad de Venta.

#### Consultar Pedidos de Unidad de Venta

Consulta los Pedidos correspondientes a una Unidad de Venta.

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
- OpenPDF
- SMTP para envío de emails

---

## Modelo y persistencia

Actualmente se encuentran implementadas las principales entidades del dominio:

- `Festival`
- `CostosFestival`
- `UnidadVenta`
- `FoodTruck`
- `PuestoDesarmable`
- `Empleado`
- `Responsable`
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
- Persistencia mediante MySQL.
- Validaciones mediante Jakarta Validation.
- Roles de usuario.
- Seguridad mediante Spring Security.
- Contraseñas cifradas con BCrypt.
- Manejo transaccional de operaciones.
- DTOs para separar la información de presentación de las entidades.
- Generación de archivos PDF.
- Envío de emails.
- Script unificado de datos de prueba.

---

## Jerarquía de Empleados

La clase `Empleado` es abstracta y utiliza herencia `JOINED`.

Actualmente existen tres tipos concretos:

### Responsable

Representa a la persona encargada de administrar una Unidad de Venta.

El Responsable:

- Tiene un usuario con rol `RESPONSABLE`.
- Se encuentra relacionado directamente con su Unidad de Venta.
- No se registra como `Cocinero` ni como `Cajero`.
- No se utiliza como empleado operativo de la Unidad.
- No se incorpora a `AsignacionEmpleado` como empleado común.
- Puede administrar únicamente la Unidad de Venta de la cual es Responsable.

### Cocinero

Empleado operativo de una Unidad de Venta.

Puede tener los siguientes roles de cocina:

- `COCINERO`
- `AYUDANTE`
- `LAVAPLATOS`

### Cajero

Empleado operativo de una Unidad de Venta.

Puede trabajar en distintos turnos, por ejemplo:

- `MANANA`
- `NOCHE`

---

# Casos de Uso implementados - Hito 2

| Caso de Uso / Corrección | Responsable | Estado | Pull Request |
|---|---|---|---|
| Inicio de sesión | Valentina Ponzo | Finalizado | PR #5 |
| Listado de Festivales | Valentina Ponzo | Finalizado | PR #6 |
| Registrar Unidad de Venta + Responsable | Valentina Ponzo | Finalizado | PR #7 |
| Consultar Unidades por Festival | Valentina Ponzo | Finalizado | PR #8 |
| Calcular Canon de Unidad de Venta | Valentina Ponzo | Finalizado | PR #10 |
| Alta y Listado de Empleados | Alejandro Escalier | Finalizado | PR #11 |
| Agregar Platos a Unidad de Venta | Cristian Medina | Finalizado | PR #14 |
| Reactivación de Empleados | Alejandro Escalier | Finalizado | PR #15 |
| Reporte de Mayores Cánones | Valentina Ponzo | Finalizado | PR #16 |
| Liquidación de Sueldos | Valentina Ponzo | Finalizado | PR #18 |
| Panel de inicio del Empleado | Alejandro Escalier | Finalizado | PR #20 |
| Consultar sueldo por Festival | Valentina Ponzo | Finalizado | PR #21 |
| Consultar Festival y Unidad actual | Valentina Ponzo | Finalizado | PR #22 |
| Consultar historial laboral | Valentina Ponzo | Finalizado | PR #23 |
| Consulta de Empleados con filtros | Alejandro Escalier | Finalizado | PR #26 |
| Envío de mail en alta de usuario | Cristian Medina | Finalizado | PR #27 |
| Alta de Pedido | Valentina Ponzo | Finalizado | PR #30 |
| Editar y eliminar platos | Cristian Medina | Finalizado | PR #31 |
| Envío de mail en reactivación de empleado | Alejandro Escalier | Finalizado | PR #36 |
| Listado de Empleados en PDF | Alejandro Escalier | Finalizado | PR #37 |
| Home pública | Valentina Ponzo | Finalizado | PR #39 |
| Script unificado de datos | Valentina Ponzo | Finalizado | PR #40 |
| Registro público de Responsable + Unidad | Valentina Ponzo | Finalizado | PR #43 |
| Detalle público de Festival + Unidades | Valentina Ponzo | Finalizado | PR #44 |
| Gestión segura de empleados por Unidad | Valentina Ponzo | Finalizado | PR #45 |
| Consulta de Plato Estrella | Cristian Medina | Finalizado | PR #46 |
| Rol supervisor del Administrador | Valentina Ponzo | Finalizado | PR #48 |
| Navegación e identidad visual | Valentina Ponzo | Finalizado | PR #49 |
| Cierre de Caja y Consulta de Rentabilidad | Cristian Prantera | Finalizado | [PR #51](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/51) |

> Cada Caso de Uso o corrección significativa fue desarrollado mediante ramas de trabajo y posteriormente integrado a `main` mediante Pull Request.

---

# Sitio público

## Home pública

Responsable: **Valentina Ponzo**  
Integrada mediante **PR #39**.

La ruta `/` funciona como página pública principal de Epicentro Gourmet.

Permite:

- Consultar Festivales sin iniciar sesión.
- Acceder al detalle público de cada Festival.
- Iniciar sesión.
- Registrarse como Responsable.
- Registrar un negocio.
- Acceder al panel correspondiente cuando el usuario ya está autenticado.

---

## Detalle público de Festival

Responsable: **Valentina Ponzo**  
Integrado mediante **PR #44**.

Ruta:

`/festivales/{idFestival}`

Permite consultar sin autenticación:

- Nombre.
- Temporada.
- Fecha de inicio.
- Fecha de finalización.
- Unidades participantes.
- Tipo de Unidad.
- Código.
- Superficie.
- Responsable.

---

# Registro público de Responsable + Unidad

Responsable: **Valentina Ponzo**  
Integrado mediante **PR #43**.

Ruta:

`/registro/responsable`

El visitante puede:

1. Seleccionar un Festival.
2. Completar los datos de su Unidad de Venta.
3. Completar sus datos personales.
4. Registrarse como `Responsable`.
5. Crear automáticamente su usuario.
6. Recibir una contraseña generada automáticamente por email.
7. Iniciar sesión con rol `RESPONSABLE`.

Durante la operación se crea:

- `Responsable`.
- `Usuario`.
- `UnidadVenta`.

El Responsable no se registra como `Cocinero` o `Cajero` y no se genera una asignación operativa en `AsignacionEmpleado`.

---

# CU - Calcular Canon de Unidad de Venta

Responsable: **Valentina Ponzo**

Permite calcular el canon de una Unidad utilizando los costos configurados para el Festival.

## FoodTruck

Se consideran:

- Superficie.
- Costo por superficie.
- Plus de electricidad cuando corresponde.

## PuestoDesarmable

Se consideran:

- Superficie.
- Costo por superficie.
- Minutos de montaje.
- Costo por montaje.

El detalle de canon puede ser consultado tanto por el Administrador como por el Responsable de la Unidad.

La misma vista adapta:

- Sidebar.
- Identificación del rol.
- Botón de regreso.

según el usuario autenticado.

---

# Gestión de costos

El Administrador puede configurar o modificar:

- Costo por superficie.
- Costo por montaje.
- Plus por electricidad.
- Sueldo base.
- Valor por año de antigüedad.
- Plus Cocinero.
- Plus Ayudante.
- Plus Lavaplatos.

Se incluyen validaciones para impedir importes negativos y campos inválidos.

---

# CU - Reporte de Mayores Cánones

Responsable: **Valentina Ponzo**

Permite al Administrador consultar las Unidades de Venta de un Festival ordenadas de mayor a menor según el canon.

Utiliza:

- `ReporteMayoresCanonDTO`
- `ReporteCanonService`
- Lógica polimórfica de cálculo de canon.

---

# Gestión de Empleados

## Alta y Listado

Responsable original: **Alejandro Escalier**

El Responsable puede registrar empleados operativos de su propia Unidad.

Durante el alta se crea:

- Empleado.
- Usuario con rol `EMPLEADO`.
- Contraseña cifrada.
- Asignación a la Unidad del Responsable.
- Envío de credenciales por email.

---

## Filtros

Responsable original: **Alejandro Escalier**

Permite buscar por:

- Nombre o apellido.
- DNI.
- Fecha de ingreso desde.
- Fecha de ingreso hasta.

---

# Gestión segura de empleados por Unidad

Responsable de la corrección: **Valentina Ponzo**  
Integrada mediante **PR #45**.

Se reforzó el aislamiento entre Unidades.

El Responsable solo puede:

- Listar empleados de su Unidad.
- Consultar empleados de su Unidad.
- Modificar empleados de su Unidad.
- Dar de baja empleados de su Unidad.
- Reactivar empleados de su Unidad.

Se consideran únicamente asignaciones vigentes.

También se implementó:

- Exclusión del Responsable del listado operativo.
- Sincronización del email del empleado con el username.
- Validación de username al modificar el email.
- Bloqueo de acceso a empleados de otras Unidades.

Pruebas realizadas:

- Roberto Gómez visualiza únicamente empleados de Burger Lanús.
- Ana Torres no visualiza empleados de Burger Lanús.
- Ana Torres no puede modificar manualmente por URL a un empleado de Burger Lanús.

---

# Listado de Empleados en PDF

Responsable: **Alejandro Escalier**

Ruta:

`GET /responsable/empleados/pdf`

El PDF contiene:

- Nombre.
- Puesto.
- DNI.
- Email.
- Fecha de ingreso.
- Estado.

Se utiliza OpenPDF y el mismo listado seguro de empleados de la Unidad del Responsable.

---

# Liquidación de Sueldos

Responsable: **Valentina Ponzo**

Permite al Responsable liquidar los haberes de los empleados operativos de su Unidad.

Incluye:

- Obtención automática de la Unidad.
- Exclusión del Responsable.
- Cálculo polimórfico para `Cocinero` y `Cajero`.
- Uso de costos del Festival.
- Persistencia del sueldo.
- `LiquidacionSueldoDTO`.
- Protección entre Unidades.

---

# Platos

## Agregar Platos

Responsable: **Cristian Medina**

Permite registrar Platos asociados a la Unidad de Venta.

## Editar y eliminar Platos

Responsable: **Cristian Medina**

Permite modificar o eliminar Platos existentes.

## Plato Estrella

Responsable: **Cristian Medina**  
Integrado mediante **PR #46**.

Permite consultar el Plato con mayor cantidad de pedidos para la Unidad/Festival correspondiente.

---

# Emails

## Alta de usuario

Responsable: **Cristian Medina**

Se envían automáticamente las credenciales generadas para:

- Alta de Empleado.
- Registro público de Responsable.

## Reactivación

Responsable: **Alejandro Escalier**

Se envía una notificación cuando el Responsable reactiva a un empleado.

---

# Panel del Empleado

Responsable original: **Alejandro Escalier**

Ruta:

`/empleado`

Permite acceder a:

- Información personal.
- Festival y Unidad actual.
- Historial laboral.
- Sueldos.
- Alta de Pedido.

---

# Consultar sueldo por Festival

Responsable: **Valentina Ponzo**

Utiliza `SueldoEmpleadoDTO`.

Permite visualizar:

- Festival.
- Temporada.
- Unidad.
- Fechas.
- Sueldo liquidado.
- Estado.

---

# Consultar Festival y Unidad actual

Responsable: **Valentina Ponzo**

Utiliza `EmpleadoActualDTO`.

Muestra:

- Unidad.
- Código.
- Tipo.
- Superficie.
- Responsable.
- Festival.
- Temporada.
- Fechas.
- Tipo de Empleado.
- Rol de cocina o turno.

---

# Consultar historial laboral

Responsable: **Valentina Ponzo**

Utiliza `HistorialLaboralDTO`.

Permite consultar todas las asignaciones del Empleado y distinguir:

- Asignación actual.
- Asignaciones finalizadas.
- Festival.
- Unidad.
- Fechas.

---

# Alta de Pedido

Responsable: **Valentina Ponzo**

Rutas:

`GET /empleado/pedidos/nuevo`

`POST /empleado/pedidos/nuevo`

Incluye:

- Unidad obtenida automáticamente.
- Festival obtenido automáticamente.
- Fecha automática.
- Platos limitados a la Unidad del empleado.
- Validación de cantidad.
- Validación de pertenencia del Plato.
- Creación de `Pedido`.
- Creación de `ItemPedido`.
- Persistencia mediante cascada.
- `RegistroPedidoDTO`.

---

# CU - Gestión de Cierres de Caja

Responsable: **Cristian Prantera**  
Integrado mediante **PR #51**.

Permite registrar y consultar cierres de caja de las Unidades de Venta.

Incluye:

- Registro de la fecha y el monto del cierre.
- Consulta de cierres por rango de fechas.
- Resumen con cantidad de cierres, recaudación total y promedio por cierre.
- Generación de comprobantes de cierre en PDF.

---

# CU - Consulta de Rentabilidad Neta

Responsable: **Cristian Prantera**  
Integrado mediante **PR #51**.

Permite consultar la rentabilidad de una Unidad de Venta para un período determinado.

Se consideran los ingresos por pedidos, los costos de producción de los platos, los sueldos y el canon para obtener la rentabilidad neta.

---

# Script unificado de datos

Responsable: **Valentina Ponzo**  
Integrado mediante **PR #40**.

Se consolidaron los datos de prueba en un único `data.sql`.

Incluye:

- Costos.
- Festivales.
- Responsables.
- Cocineros.
- Cajeros.
- Usuarios.
- Unidades de Venta.
- Platos.
- Asignaciones laborales.
- Pedidos.
- Ítems de Pedido.
- Cierres de Caja.

El script fue actualizado para utilizar el subtipo `Responsable` correctamente.

---

# Roles del sistema

## ADMINISTRADOR

Panel:

`/admin`

Funciona principalmente como rol de **supervisión**.

Puede:

- Consultar Festivales.
- Consultar Unidades.
- Configurar costos.
- Modificar costos.
- Consultar cánones.
- Consultar empleados.
- Consultar reportes.
- Consultar mayores cánones.

Mediante **PR #48** se eliminaron los accesos habituales para registrar nuevas Unidades desde Administración.

El alta normal de un negocio se realiza desde el registro público.

---

## RESPONSABLE

Panel:

`/responsable`

Puede:

- Consultar su Unidad.
- Consultar su Festival.
- Consultar su canon.
- Gestionar empleados.
- Descargar listado de empleados en PDF.
- Liquidar sueldos.
- Gestionar Platos.
- Consultar Plato Estrella.

No puede administrar empleados de otras Unidades.

---

## EMPLEADO

Panel:

`/empleado`

Puede:

- Consultar su información.
- Consultar Festival y Unidad actual.
- Consultar historial laboral.
- Consultar sueldos.
- Registrar Pedidos.

---

# Seguridad

La aplicación utiliza Spring Security.

## Rutas públicas

- `/`
- `/login`
- `/registro/**`
- `/festivales/**`
- `/css/**`
- `/js/**`
- `/images/**`

## Administrador

Rutas:

`/admin/**`

Rol requerido:

`ADMINISTRADOR`

## Responsable

Rutas:

`/responsable/**`

Rol requerido:

`RESPONSABLE`

## Empleado

Las rutas del Empleado utilizan el esquema correspondiente definido en la configuración de seguridad.

---

# Pruebas de seguridad

Se verificó manualmente:

## Admin → Responsable

Intento:

`/responsable`

Resultado:

`403 Forbidden`

## Responsable → Admin

Intento:

`/admin`

Resultado:

`403 Forbidden`

## Empleado → Admin / Responsable

Resultado:

Acceso bloqueado.

La seguridad no depende únicamente de ocultar enlaces en las vistas.

---

# Navegación

Corrección integrada mediante **PR #49**.

Regla utilizada:

- Administrador → `/admin`.
- Responsable → `/responsable`.
- Empleado → `/empleado`.
- Login y sitio público → `/`.

También se corrigió la vista compartida del Canon para adaptarse correctamente a Admin y Responsable.

---

# Interfaz e identidad visual

Mediante **PR #49** se unificó la interfaz general.

Se utiliza:

- Azul como color institucional principal.
- Naranja como acento gastronómico.
- Tarjetas y paneles coherentes.
- Badges.
- Botones consistentes.
- Menús laterales por rol.
- Diseño responsive.

La pantalla de Login utiliza la imagen:

`festival-gastronomico.jpg`

junto con un overlay azul/naranja para mantener continuidad visual con la Home pública.

---

# Requisitos generales

## Envío de email

**Implementado.**

Incluye:

- Alta de Empleado.
- Registro de Responsable.
- Reactivación de Empleado.

## Reportes mediante DTO

**Implementado.**

Entre los DTO utilizados:

- `ReporteMayoresCanonDTO`
- `LiquidacionSueldoDTO`
- `SueldoEmpleadoDTO`
- `EmpleadoActualDTO`
- `HistorialLaboralDTO`
- `RegistroPedidoDTO`
- `RegistroEmpleadoDTO`
- `ModificarEmpleadoDTO`
- `RegistroUnidadResponsableDTO`

## Generación de PDF

**Implementada.**

Se generan:

- Listados de empleados de la Unidad del Responsable.
- Comprobantes de cierre de caja.

## Script final de datos

**Implementado.**

Existe un único `data.sql` para las pruebas integrales.

---

# Pull Requests de correcciones finales

## PR #39 - Home Pública

Se incorporó la página pública principal.

## PR #40 - Script unificado

Se consolidaron los datos necesarios para realizar pruebas reproducibles.

## PR #43 - Registro público Responsable + Unidad

Se adaptó el alta para que el propio Responsable registre su negocio.

También se incorporó el subtipo `Responsable`.

## PR #44 - Detalle público de Festival

Los visitantes pueden consultar las Unidades participantes sin autenticación.

## PR #45 - Gestión segura de empleados

Cada Responsable solo administra empleados actualmente asignados a su Unidad.

## PR #48 - Rol supervisor del Administrador

Se eliminaron accesos operativos de alta de Unidad desde Administración.

## PR #49 - Navegación e identidad visual

Se corrigieron las rutas de regreso de cada rol y se unificó el diseño visual de Home, Login y paneles internos.

## PR #51 - Cierre de Caja y Consulta de Rentabilidad

Se incorporaron el registro y consulta de cierres de caja, los filtros por fecha, el resumen de recaudación, la generación de comprobantes PDF y la consulta de rentabilidad neta de una Unidad de Venta.

---

# Metodología Git

Para cada Caso de Uso o corrección:

1. Se crea una rama desde `main`.
2. Se desarrolla la funcionalidad.
3. Se compila y prueba.
4. Se realiza un commit descriptivo.
5. Se publica la rama.
6. Se crea un Pull Request.
7. Se revisan conflictos.
8. Se realiza el merge.
9. Se actualiza `main`.
10. Se documentan los cambios.

---

# Base de datos

La aplicación utiliza MySQL.

Ejemplo de variables de entorno:

`DB_USER`

`DB_PASSWORD`

Opcionalmente puede configurarse:

`DB_URL`

El puerto puede variar según la configuración local.

Para el Administrador pueden utilizarse:

`ADMIN_USERNAME`

`ADMIN_PASSWORD`

No se recomienda versionar contraseñas reales dentro del repositorio.

---

# Ejecución

Ingresar al proyecto:

`cd epicentrogourmet`

Compilar:

`./mvnw -DskipTests compile`

Ejecutar:

`./mvnw spring-boot:run`

Acceder:

`http://localhost:8080/`

---

# Estado actual

Actualmente el proyecto cuenta con:

- JPA/Hibernate.
- MySQL.
- Spring Security.
- BCrypt.
- Roles `ADMINISTRADOR`, `RESPONSABLE` y `EMPLEADO`.
- Home pública.
- Login personalizado.
- Registro público de Responsable.
- Detalle público de Festival.
- Dashboard de Administración.
- Panel del Responsable.
- Panel del Empleado.
- Seguridad por rol.
- Seguridad por Unidad.
- Listado de Festivales.
- Unidades por Festival.
- Costos.
- Canon.
- Reporte de mayores cánones.
- Alta de Empleados.
- Modificación de Empleados.
- Baja lógica.
- Reactivación.
- Filtros.
- PDF de Empleados.
- Alta y gestión de Platos.
- Plato Estrella.
- Envío de emails.
- Generación automática de contraseñas.
- Liquidación de sueldos.
- Consulta de sueldos.
- Festival y Unidad actual.
- Historial laboral.
- Alta de Pedido.
- `Pedido` e `ItemPedido`.
- Gestión de Cierres de Caja.
- Consulta de Cierres de Caja por fecha.
- Resumen de recaudación y promedio por cierre.
- Generación de PDF de Cierres de Caja.
- Consulta de Rentabilidad Neta.
- Script unificado de datos.
- Menús laterales por rol.
- Navegación corregida.
- Identidad visual unificada.
- Flujo mediante ramas y Pull Requests.

---

# Verificación final

Antes de la entrega se recomienda verificar:

- `main` actualizado.
- Sin conflictos pendientes.
- `./mvnw -DskipTests compile` exitoso.
- MySQL disponible.
- `data.sql` ejecutado.
- Login de los tres roles.
- Home pública.
- Detalle público de Festival.
- Registro público del Responsable.
- Envío de email.
- Seguridad de Admin.
- Seguridad de Responsable.
- Seguridad de Empleado.
- Gestión de empleados por Unidad.
- PDF.
- Canon.
- Cierres de Caja.
- Filtros y resumen de recaudación.
- Consulta de Rentabilidad Neta.
- Navegación entre paneles.
- Que cada integrante pueda explicar sus Casos de Uso.
