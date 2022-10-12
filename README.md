# Reto Kruger

## Tecnologias utilizadas

- [x] Spring Boot 2.6.12
- [x] Spring Data JPA
- [x] Spring Security
- [x] Swagger
- [x] Lombok
- [x] PostgreSQL

## Instalación

### Requisitos

- Java 11
- PostgreSQL 13

### Pasos

1. Descargar del release el archivo inventario-0.0.1-SNAPSHOT.jar
2. Crear la base de datos con el nombre "inventory"
3. Crear el usuario "inventory" con contraseña "inventory"
4. Ejecutar el archivo .jar en una terminal con el siguiente comando:

`java -jar inventario-0.0.1-SNAPSHOT.jar`

## Endpoints

El unico que tiene acceso al sistema por primera vez es el admin, el cual se crea por defecto con el siguiente usuario y contraseña:

- Usuario: admin
- Contraseña: admin

### Para obtener todos los empleados

- GET http://localhost:8080/api/employee

### Para obtener un empleado por su identificacion

- GET http://localhost:8080/api/employee/{identification}

### Para crear un empleado nuevo (Solo el admin puede crear empleados) -- Los campos requeridos son: identification, name, lastName, email..

- POST http://localhost:8080/api/employee

### Para actualizar un empleado

- PUT http://localhost:8080/api/employee/{identification}

### Para eliminar un empleado

- DELETE http://localhost:8080/api/employee/{identification}

### Para obtener los empleados por su estado de vacunación

- GET http://localhost:8080/api/employee/vaccinated/{vaccinated}

### Para obtener los empleados por su tipo de vacuna

- GET http://localhost:8080/api/employee/vaccine/{vaccine}

### Para obtener los empleados por un ranfo de fechas, el formato de la fecha es yyyy-MM-dd

- GET http://localhost:8080/api/employee/date/{date1}/{date2}

## Documentación

La documentación de los endpoints es creada por Swagger, para acceder a ella se debe ingresar a la siguiente url:

`http://localhost:8080/swagger-ui/`

Usar las credenciales:

- Usuario: admin
- Contraseña: admin

## Información adicional

- Cuando se crea un empleado nuevo, se le asigna un rol de usuario por defecto "EMPLOYEE". El usuario generado esta conformado por el primer nombre, el primer apellido del empleado y las 3 primeras letras de la identificacion, por ejemplo: si el empleado se llama "Juan Perez", con identificacion "1234567890" el usuario sera "juan.perez123".
- La contraseña es generada de manera aleatoria y nos devuelve el usuario y la contraseña en el body de la respuesta.
- Al iniciar el proyecto se crean las vacunas, los roles y el usuario admin por defecto.

## Esquema de datos
![Esquema](https://user-images.githubusercontent.com/52105514/195209388-91f499c8-02b6-4646-b7f1-17c8a0476df5.jpg)

## Ejemplos de peticiones
- Creacion de un nuevo empleado
![Peticion 1](https://user-images.githubusercontent.com/52105514/195222058-68c7a853-b22c-4bac-ae88-46d40ef1aa04.jpg)
![Peticion 2](https://user-images.githubusercontent.com/52105514/195223523-b7aa7609-407a-4404-aeba-65fc5e4d1baa.jpg)
- Obtener empleados en una fecha determinada de vacunacion
![Peticion 4](https://user-images.githubusercontent.com/52105514/195223605-0a20f893-882a-4d30-b117-8d93f5d07ed3.jpg)



