# backend-jaya-coin-converter

### Propósito

Desafio realizado pela Jaya tech onde tem como objetivo à construir de um conversor de moeda.
Neste projeto quis dedicar ao máximo para aprender/aperfeiçoar sobre a estrutura de um projeto **Kotlin** baseado
em spring boot.Aplicado a estrutura MVC com autenticação em JWT, conectado a um banco de dados **PostgreSQL**, quis
desenvolver um ambiente completo desde o desenvolvimento até a entrega em produção no ambiente
heroku.

Aplicação em deploy: https://backend-jaya-coin-converter-dc70732ba7c9.herokuapp.com/api

## Ideias/Melhorias

- ✅ **Swagger**: Inclusão do swagger na aplicação
- ❌ **Schema Banco**: Criação do schema em produção
- ❌ **RabbitMQ**: Implementar mensageria para processos mais complexos de multi concorrência.

## 🎓 Aprendizado
-  **Kotlin** - Aprimoramento dos conhecimentos basicos que tinha sobre o ambiente kotlin e java integrado.
-  **PostgreSQL** - Melhoria do entendimento de como funciona o banco de dados em produção como suas sutilezas.
-  **CI/CD** - Introdução de uma esteira de implantação funcional
-  **Integração Front** - Entendimento de CORS e outros problemas encontrados durante o desenvolvimento

## 🛠️ Tecnologias utilizadas

- **Linguagens/Back-end**: Kotlin(Multiplataforma e tipagem limpa), Java 17(Familiaridade no ecosistema Java e com muitas features de desepenho corrigidas do passado)
- **Framework**: Spring Boot 3.x - Servidor de facil deploy e consistencia.
- **CI/CD**: Github Actions
- **Cobertura de testes**: JaCoCo Reports - Cobetura de testes aceita pelo SonarQube por exemplo.
- **Execução de testes**: JUnit
- **Geração de token**: JWT Token

## Iniciado o projeto

### Requisitos

- **Versão Java necessária**: Java 17
- **Gradle**: Instalado ou utilizar o ./gradlew do próprio projeto

### Instalação

1. **Clone do projeto**:
 ```bash
git clone https://github.com/gbmdevs/backend-jaya-coin-converter.git
cd backend-jaya-coin-converter
 ```
2. **Compilar e executar o projeto: Utilizar o perfil test no active profiles**:
```bash
./gradlew bootRun --args='--spring.profiles.active=test'
```
## 🧪 Execução testes unitários
```bash
./gradlew test
```
## 📚 API - Documentação em Swagger

Local: http://localhost:9439/api/swagger-ui/index.html

## 📈 Informação status servidor(Actuator)

Listagem das metricas do Servidor

- Health: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`