Here is a simple `README.MD` for your Java Spring Boot project using Maven and MySQL with Docker Compose.

---

# Project Setup

## Requisitos

- Java 17+ (or your project's required version)
- Maven 3.6+
- Docker & Docker Compose

## Running MySQL with Docker Compose

Comenzar la mysql de prueba con `compose.yml` file:

```sh
docker compose -f compose.yml up -d
```

Esto iniciará un contenedor MySQL 

## Running the Application

1. Construir el proyecto con Maven:

   ```sh
   mvn clean install
   ```

2. Correr la aplicación:

   ```sh
   mvn spring-boot:run
   ```

## Stopping the Database

To stop the MySQL container:

```sh
docker compose -f compose.yml down
```

---

