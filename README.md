# Geolocation Feasibility API

API desenvolvida em Java 21 com Spring Boot para análise de viabilidade geográfica baseada em raio de distância, utilizando cálculo de Haversine e dataset carregado em memória.

## Tecnologias
- Java 21
- Spring Boot
- Maven
- Docker
- Spring Validation
- Spring Actuator
- Swagger (OpenAPI)

## Funcionalidades
- Consulta de pontos dentro de um raio geográfico
- Cálculo de distância utilizando fórmula de Haversine
- Paginação de resultados
- Filtragem por status (ACTIVE, RESERVED)
- Dataset carregado em memória na inicialização
- Tratamento centralizado de erros
- Logging de requisições, respostas e erros
- Geração de RequestId por requisição
- Medição de tempo de resposta via headers

## Endpoint principal
- GET /api/feasibility
  
```bash
GET /api/feasibility?latitude=-23.556456&longitude=-46.635653&radius=100
```
- Parâmetros

| Parâmetro | Obrigatório | Descrição                   |
| --------- | ----------- | --------------------------- |
| latitude  | Sim         | Latitude entre -90 e 90     |
| longitude | Sim         | Longitude entre -180 e 180  |
| radius    | Sim         | Raio em metros (10 a 1000)  |
| page      | Não         | Página (default: 0)         |
| size      | Não         | Tamanho da página (max: 20) |

## Arquitetura
- Controller → exposição da API
- Service → regras de negócio
- Domain → entidades e modelo
- Repository → dataset em memória
- DTO → comunicação externa
- Exception Handler → tratamento centralizado de erros
- Filter → observabilidade (RequestId + tempo de resposta)

## Observabilidade
### A aplicação possui:
- Log de requisições
- Log de erros
- Log de tempo de execução
- Header X-Request-Id (UUID por requisição)
- Header X-Response-Time (tempo em ms)

## Como rodar localmente

```bash
mvn spring-boot:run
```

## Como rodar com Docker
### Build da imagem
```bash
docker build -t geolocation-api .
```
### Executar container
```bash
docker run -p 8080:8080 geolocation-api
```
## A aplicação irá subir em:
```bash
http://localhost:8080
```

## Acessar documentação
```bash
http://localhost:8080/swagger-ui/index.html
```

## Health Check
```bash
GET /actuator/health
```

## Executar testes automatizados
```bash
mvn clean test
```

## Logs
### A aplicação utiliza log em arquivo com rotação automática (rolling file), com tamanho máximo de 1MB por arquivo, conforme solicitado no desafio técnico, localizado em /logs/app.log

## Exemplo de resposta de sucesso
```bash
GET /api/feasibility?latitude=-23.556456&longitude=-46.635653&radius=100
```

### Resposta (200 OK)
```bash
[
  {
    "id": 34,
    "name": "CTO-RJ-0004",
    "latitude": -23.551000,
    "longitude": -46.632000,
    "distanceMeters": 15.56
  },
  {
    "id": 35,
    "name": "CTO-RJ-0005",
    "latitude": -23.561000,
    "longitude": -46.637000,
    "distanceMeters": 16.78
  }
]
```

### Exemplo de resposta sem resultados
```bash
[]
```

## Exemplo de erro de validação
```bash
GET /api/feasibility?longitude=-46.635653&radius=100
```
### Resposta (400 Bad Request)
```bash
{
  "code": "400",
  "reason": "empty field",
  "message": "latitude is mandatory",
  "status": "bad request",
  "timestamp": "2025-02-13T14:25:00Z"
}
```

### Exemplo de erro interno
```bash
GET /api/feasibility?longitude=-46.635653&radius=100
```
### Resposta (500 Internal Server Error)
```bash
{
  "code": "500",
  "reason": "internal server error",
  "message": "general fail",
  "status": "internal server error",
  "timestamp": "2025-02-13T14:25:00Z"
}
```

## Estrutura do projeto

```bash
src/
 └── main/
     ├── java/com/beatriz/geolocation_feasibility_api/
     │    ├── controller/
     │    ├── service/
     │    ├── domain/
     │    ├── repository/
     │    ├── dto/
     │    ├── exception/
     │    ├── filter/
     │    ├── config/
     │    └── GeolocationFeasibilityApiApplication.java
     └── resources/
         ├── application.properties
         ├── logback-spring.xml
         └── data/
              └── points.json
```

## Autor
- Desenvolvido por Ana Beatriz