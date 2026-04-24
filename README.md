# Geolocation Feasibility API

API desenvolvida em Spring Boot 21 para análise de viabilidade de localização.

## Tecnologias
- Java 21
- Spring Boot
- Maven
- Docker

## Como rodar localmente

```bash
mvn spring-boot:run
```
## A aplicação irá subir em:
```bash
http://localhost:8080
```

## Como rodar com Docker

```bash
docker build -t geolocation-api .
docker run -p 8080:8080 geolocation-api
```

## Acessar documentação

```bash
http://localhost:8080/swagger-ui/index.html
```

## Estrutura do projeto

```bash
src/
 └── main/
     ├── java/com/beatriz/geolocationfeasibility/
     │    ├── controller/
     │    ├── service/
     │    ├── domain/
     │    ├── repository/
     │    ├── dto/
     │    ├── mapper/
     │    ├── exception/
     │    ├── validator/
     │    ├── util/
     │    ├── logging/
     │    ├── config/
     │    └── health/
     └── resources/
         └── application.properties
```

## Sobre o projeto

- Este projeto tem como objetivo simular uma API de análise de viabilidade de localização, utilizando boas práticas de desenvolvimento backend com Spring Boot.

## Status do projeto

- Em desenvolvimento

## Autor

- Desenvolvido por Ana Beatriz