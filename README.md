# RIU Backend Challenge

---

### Aplicación desarrollada para el challenge técnico de Mindata.

La aplicación está compuesta por dos microservicios:
- riu-searcher: Expone endpoints REST, permite consultar y publicar búsquedas
- riu-writer: Consume las búsquedas y las almacena en la base de datos

---

## Requisitos

Para levantar la aplicación se requieren las siguientes herramientas:
- Docker
- Docker Compose

---

## Levantar la aplicación

Desde la raíz del proyecto, ejecutar el siguiente comando:

```
docker compose up --build
```

Este comando construye los dos microservicios, genera sus archivos .jar, y levanta todos los servicios necesarios.

---

## Detener la aplicación

Desde la raíz del proyecto, ejecutar el siguiente comando:

```
docker compose down
```

Para eliminar también los volúmenes:

```
docker compose down -v
```

---

## Documentación (Swagger)

La documentación de la API se encuentra en la siguiente URL:

```
http://localhost:3500/swagger-ui/index.html
```

---

## Endpoints

### GET ```/api/hotel-search/v1/count?searchId={searchId}```

Consulta una búsqueda basándose en su identificador, y muestra la cantidad de búsquedas idénticas registradas.

#### Ejemplo (URL)

```
http://localhost:3500/api/hotel-search/v1/count?searchId=4f329977-8339-4d6f-9988-348f98ef4a1d
```

### POST ```/api/hotel-search/v1/search```

Asigna un identificador a la búsqueda y la publica para su procesamiento.

#### Ejemplo (BODY)

```
{
    "hotelId": "1234aBc",
    "checkIn": "2026-08-24",
    "checkOut": "2026-09-24",
    "ages": [59, 54, 24, 20]
}
```