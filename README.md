# Franchise API

Prueba técnica API REST para gestionar franquicias, sucursales y productos.

## Requisitos

- Java
- Docker

## Colección Postman

Archivo API.postman_collection.json

## Despliegue en Producción

- URL de la API en producción: [API](https://backend-developer-gdh2frajfdezhpgm.centralus-01.azurewebsites.net/api/)
- Estado de la API en
  producción: [Estado](https://backend-developer-gdh2frajfdezhpgm.centralus-01.azurewebsites.net/api/status)
- Documentación de la API en
  producción: [Documentación](https://backend-developer-gdh2frajfdezhpgm.centralus-01.azurewebsites.net/api/swagger-ui/index.html)

## Despliegue Local con Docker

Para desplegar la aplicación localmente usando Docker, sigue estos pasos:

### 1. Clonar el repositorio

Clona el repositorio o accede a la carpeta del proyecto.

### 2. Levantar los contenedores

Abre una terminal en la carpeta raíz del proyecto y ejecuta:

```
docker-compose up --build
```

Este comando hará lo siguiente:

- Descargará las imágenes necesarias
- Compilará la aplicación
- Levantará una base de datos PostgreSQL
- Levantará el servidor de la API

### 3. Esperar a que todo inicie

Espera a que la aplicación termine de iniciarse. Deberías ver un mensaje similar a:

```
franchise-api | Started FranchiseApiApplication in X seconds
```

### 4. Acceder a la API

Una vez que la aplicación está en ejecución, puedes acceder a:

- **API Base**: http://localhost:8080/api
- **Documentación Swagger**: http://localhost:8080/api/swagger-ui.html

### 5. Detener la aplicación

Para detener los contenedores, presiona Ctrl+C en la terminal o ejecuta:

```
docker-compose down
```

## Estructura de la API

### Endpoints principales

- **Status**
    - GET /api/status - Verificar estado de la API

- **Franquicias**
    - POST /api/franchises - Crear franquicia
    - GET /api/franchises/{id} - Obtener franquicia
    - PATCH /api/franchises/{id}/name - Actualizar nombre (plus)

- **Sucursales**
    - POST /api/franchises/{franchiseId}/branches - Crear sucursal
    - GET /api/franchises/{franchiseId}/branches/{branchId} - Obtener sucursal
    - PATCH /api/franchises/{franchiseId}/branches/{branchId}/name - Actualizar nombre (plus)

- **Productos**
    - POST /api/branches/{branchId}/products - Crear producto
    - GET /api/branches/{branchId}/products/{productId} - Obtener producto
    - PATCH /api/branches/{branchId}/products/{productId}/stock - Actualizar stock (plus)
    - DELETE /api/branches/{branchId}/products/{productId} - Eliminar producto

- **Reportes**
    - GET /api/franchises/{franchiseId}/reports/max-stock-products - Productos con mayor stock por sucursal

## Configuración de la base de datos

La base de datos PostgreSQL se configura automáticamente con:

- Usuario: postgres
- Contraseña: postgres
- Base de datos: franchise_db
- Puerto: 5432

## Notas

- La aplicación crea automáticamente las tablas al iniciar
- El servidor está accesible en el puerto 8080
- Servicio desplegado en Azure App Service
- Documentación generada con Swagger
- Configuración local con Docker Compose
