# Spring


## Aplicação Spring Boot

[Initializr](https://start.spring.io/) é uma ferramenta que permite criar um projeto Spring Boot de forma rápida e fácil. Você pode escolher as dependências, a versão do Java e outras configurações.

### Estrutura do projeto

```bash
src
└── main
    ├── java
    │   └── poo
    │       └── spring
    │           ├── Application.java
    └── resources
        └── application.yaml
```

Compilar, estando no diretório do microsserviço, execute o comando:
``` bash
../account-service> mvn clean package
```

Para rodar o docker compose, execute o comando, no diretório `api`:
``` bash
docker compose up -d --build
```
