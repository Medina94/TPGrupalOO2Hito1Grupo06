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

| Reactivación de Empleados | Alejandro Escalier | Finalizado | `CU-Reactivacion-Empleados` | [PR #N](https://github.com/Medina94/TPGrupalOO2Hito1Grupo06/pull/18) |

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
- Validaciones específicas para FoodTruck.
- Validaciones específicas para PuestoDesarmable.
- Validaciones específicas para Cocinero.
- Validaciones específicas para Cajero.

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

### CU - Alta y Listado de Empleados

Responsable: **Alejandro Escalier**

Permite al Responsable registrar nuevos empleados y consultar los empleados cargados en el sistema.

**Rutas:**

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/responsable/empleados` | Listado de empleados |
| `GET` | `/responsable/empleados/nuevo` | Formulario de alta |
| `POST` | `/responsable/empleados/nuevo` | Registro del empleado |

**Alta de empleado**

Tipos de empleado soportados:

- `Cocinero`, con rol de cocina: `COCINERO`, `AYUDANTE` o `LAVAPLATOS`.
- `Cajero`, con turno: `MANANA` o `NOCHE`.

Durante el registro, en una única operación transaccional, se crea:

- El empleado (`Cocinero` o `Cajero`).
- El usuario de acceso con rol `EMPLEADO`, vinculado al empleado.
- La contraseña cifrada con BCrypt.

Validaciones implementadas:

- Nombre y apellido obligatorios.
- DNI obligatorio, mayor a cero y no repetido.
- Email obligatorio, con formato válido y no repetido.
- Fecha de nacimiento obligatoria y anterior a la fecha actual.
- Empleado mayor de 18 años.
- Fecha de ingreso obligatoria y no futura.
- Tipo de empleado obligatorio.
- Rol de cocina obligatorio para `Cocinero` y turno obligatorio para `Cajero`.
- Nombre de usuario obligatorio y único.
- Contraseña obligatoria de al menos 6 caracteres.
- Mensajes de error por campo y mensaje general ante datos duplicados.
- Mensaje de confirmación al registrar correctamente.

**Listado de empleados**

- Tabla con todos los empleados registrados, ordenados por DNI.
- Datos visibles: nombre y apellido, puesto (`Cajero` o `Cocinero`), DNI, email, fecha de ingreso y estado (`Activo` / `Inactivo`).
- Contador de empleados registrados.
- Botón de acceso rápido a "Nuevo Empleado".
- Manejo del listado vacío.
- Visualización de la Unidad de Venta del Responsable y acceso a su canon.

---

### CU - Reactivación de Empleados

Responsable: **Alejandro Escalier**

Permite volver a dar de alta a un empleado de la base de datos.

**Rutas:**

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/responsable/empleados/{id}/reactivar` | Reactivación |

Funcionalidades:

- **Reactivación:** el empleado y su usuario vuelven a quedar activos y se restablece el acceso.
- En el listado, cada empleado muestra el botón que corresponde a su estado ("Dar de baja" si está activo, "Dar de alta" si está inactivo).
- Ambas acciones piden confirmación antes de ejecutarse.
- Mensajes de éxito o error luego de cada operación.
- Operación transaccional: se actualizan el empleado y su usuario en conjunto.

---

### Navegación del Responsable

Responsable: **Alejandro Escalier**

Se incorporó el flujo de navegación para el rol `RESPONSABLE`:

- Tras iniciar sesión, el Responsable es redirigido a `/responsable/empleados`; el resto de los roles va a la página de inicio.
- Si un Responsable ingresa a `/`, también es redirigido a su panel.
- Nuevo menú lateral para el Responsable ("Mi Unidad"), con acceso a Gestión de empleados y accesos futuros marcados como "Próximamente".
- Se agregó el fragmento de menú lateral para el Empleado, con sus accesos futuros.
- Reglas de seguridad: las rutas `/responsable/**` requieren el rol `RESPONSABLE`.

---

## Roles del sistema

La aplicación contempla tres tipos principales de usuario.

### Administrador

Puede gestionar información general del sistema.

Funcionalidades actuales y previstas:

- Consultar Festivales.
- Consultar Unidades de Venta.
- Registrar Unidad de Venta y Responsable.
- Configurar costos de Festivales.
- Consultar cánones.
- Consultar ranking de facturación.
- Consultar reporte de mayores cánones.

### Responsable

Administra la Unidad de Venta que tiene asignada.

Funcionalidades actuales:

- Consultar su Unidad de Venta y su canon.
- Gestionar empleados: alta, listado, modificación, baja lógica y reactivación.

Funcionalidades previstas:

- Agregar platos.
- Consultar Plato Estrella.
- Liquidar haberes.
- Consultar reportes de ventas.
- Consultar rentabilidad.
- Enviar credenciales a empleados.

### Empleado

Consulta información relacionada con su actividad laboral.

Funcionalidades previstas:

- Consultar Festival y Unidad actual.
- Consultar historial laboral.
- Consultar sueldo recibido por Festival.
- Descargar recibo de sueldo en PDF.

Los empleados de tipo `Cajero` podrán además realizar el cierre diario de caja.

---

## Interfaz y navegación

Se implementó una interfaz web administrativa con:

- Barra superior.
- Menú lateral.
- Dashboard de Administración.
- Accesos rápidos.
- Navegación entre Festivales, Unidades, Costos y Canon.
- Diseño reutilizable mediante CSS.
- Fragmentos Thymeleaf para los menús laterales.

Los menús laterales se encuentran definidos para los tres roles:

- Administrador.
- Responsable (con acceso activo a la gestión de empleados).
- Empleado (accesos previstos).

---

## Casos de Uso en desarrollo / pendientes

Entre las funcionalidades restantes se encuentran:

- Filtros y búsqueda de empleados.
- Consultar historial laboral del empleado.
- Consultar Festival y Unidad actual del empleado.
- Agregar platos a una Unidad de Venta.
- Consultar Plato Estrella.
- Enviar credenciales mediante email.
- Liquidar sueldo de empleados.
- Consultar sueldo recibido por Festival.
- Generar recibo de sueldo en PDF.
- Generar reporte de ventas.
- Calcular rentabilidad neta.
- Calcular rentabilidad entre fechas.
- Ranking de facturación.
- Reporte de mayores cánones.
- Cierre de caja del Cajero.
- Navegación específica para Empleado.

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
