
# Schedule-Core UFU

## 🎯 Sobre o Projeto

**Schedule-Core** é o backend da plataforma de agendamento de espaços esportivos para a Universidade Federal de Uberlândia (UFU). O projeto foi desenvolvido com o objetivo de modernizar e automatizar o processo de reserva de ginásios e quadras, que antes era realizado de forma manual e burocrática.

A aplicação centraliza a consulta de horários e a realização de agendamentos, reduzindo a carga de trabalho administrativo e prevenindo conflitos de horários.

## ✨ Funcionalidades Principais

  * **Gestão de Agendamentos:**
      * Criar, consultar e cancelar agendamentos.
      * Listar todos os agendamentos de um usuário específico.
      * Visualizar a grade de horários por dia e por ginásio.
  * **Administração de Espaços:**
      * Cadastrar e remover ginásios e outros espaços esportivos.
      * Definir restrições de datas (ex: feriados, eventos) para impedir agendamentos.
  * **Notificações:**
      * Envio de e-mails automáticos para confirmar o cancelamento de uma reserva.
  * **Validação de Regras de Negócio:**
      * Utiliza uma cadeia de validações (Chain of Responsibility) para garantir a integridade dos agendamentos, verificando duplicidade, restrições de data, horário de funcionamento do ginásio e outros.

## 🔐 Autenticação e Autorização (JWT + Spring Security)

A API utiliza autenticação via **JWT (JSON Web Token)** combinada com **Spring Security**, com as seguintes características:

- **Login e Geração de Token:**
  - O endpoint `/auth/token` permite autenticação de usuários cadastrados e retorna um JWT assinado com SHA-256.
  - O token contém claims como `matricula`, `email`, `name` e `role`, com validade de 2 horas.

- **Proteção de Rotas:**
  - Endpoints públicos: `/auth/**`
  - Endpoints autenticados: `/v1/schedule/**`
  - Endpoints restritos ao papel `ADMIN`: `/v1/adm/**`

- **Validação de Token:**
  - Tokens são validados via filtro personalizado (`JwtAuthenticationFilter`) usando `java-jwt` (Auth0).
  - A assinatura é feita com base em um hash SHA-256 do e-mail institucional (`spring.mail.username`), aumentando a segurança.

- **Criação Automática de Administrador:**
  - Ao iniciar a aplicação, um usuário administrador é criado automaticamente caso ainda não exista no banco. O e-mail é definido em `spring.mail.username` e recebe o papel `ADMIN`.

### Endpoints de Autenticação (`/auth`)

| Método | Rota           | Descrição |
|--------|----------------|-----------|
| `POST` | `/auth/email`  | Envia um código de verificação para o e-mail institucional informado. |
| `POST` | `/auth/verify` | Valida o código enviado por e-mail e cria/atualiza o usuário. Retorna um JWT válido. |
| `POST` | `/auth/token`  | Gera um novo JWT para um usuário já registrado com e-mail `@ufu.br`. |
| `GET`  | `/auth/user`   | Retorna os dados do usuário autenticado com base no token enviado no header. |

#### 🔐 Fluxo completo de autenticação:

1. O usuário envia seu e-mail institucional no endpoint `/auth/email`:
```json
{ "email": "usuario@ufu.br" }
```
2. O usuário recebe um código por e-mail e o envia para `/auth/verify`:
```json
{ "email": "usuario@ufu.br", "codigo": "123456" }
```
3. O sistema retorna um token JWT válido. Para renovar, o usuário pode usar `/auth/token`:
```json
{ "email": "usuario@ufu.br" }
```
4. Para consultar dados do usuário autenticado:
```
GET /auth/user
Authorization: Bearer <token>
```

## 🛠️ Tecnologias Utilizadas

  * **Backend:** Java 17, Spring Boot 3.5.0
  * **Segurança:** Spring Security, Auth0 Java JWT
  * **Banco de Dados:** PostgreSQL
  * **Gerenciador de Dependências:** Maven
  * **Containerização:** Docker (via `docker-compose.yml`)

## 🏗️ Arquitetura: Ports and Adapters (Hexagonal)

O projeto foi estruturado seguindo o padrão de **Arquitetura Hexagonal**, que isola a lógica de negócio de dependências externas e facilita a manutenção e testabilidade do sistema.

  * **Hexágono (Core do Domínio):**
      * Localizado no pacote `domains`, contém a lógica de negócio pura e agnóstica a tecnologias.
      * **`ports`**: Define as interfaces (Portas) que o domínio usa para se comunicar com o mundo exterior.

  * **Adaptadores (Infrastructure):**
      * O pacote `infrasctructure` contém as implementações concretas das portas.
      * **Inbound:** Controllers REST (ex: `ScheduleController`).
      * **Outbound:** Banco de dados, e-mail, etc.

## 🧪 Testes

Atualmente, o projeto **não possui uma suíte de testes unitários ou de integração automatizados**.

Os testes funcionais da API são realizados através da collection do **Insomnia**, que pode ser encontrada no arquivo: `src/test/Insomnia_2025-07-10.yaml`. Esta collection contém requisições prontas para todos os endpoints da aplicação, facilitando a validação manual do comportamento esperado.

## 🗺️ API Endpoints

A API está versionada sob o prefixo `/v1`. Abaixo estão os endpoints principais.

### Agendamentos (`/v1/schedule`)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `GET` | `/schedule` | Busca horários disponíveis e agendados para uma data. |
| `POST` | `/schedule` | Cria um novo agendamento. |
| `POST` | `/schedule/delete` | Cancela um agendamento existente. |
| `GET` | `/schedule/{matricula}` | Retorna todos os agendamentos de um usuário. |

### Administração (`/v1/adm`)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/ginasio` | Cria ou atualiza um ginásio. |
| `GET` | `/ginasio` | Lista todos os ginásios. |
| `POST` | `/ginasio/delete/{id}` | Remove um ginásio. |
| `POST` | `/restricao` | Adiciona uma restrição de data em um ginásio. |
| `GET` | `/restricao` | Lista todas as restrições. |
| `GET` | `/schedule` | Lista todos os agendamentos do sistema. |

## 🚀 Como Executar o Projeto

### Pré-requisitos

  * [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
  * [Apache Maven](https://maven.apache.org/download.cgi)
  * [Docker](https://www.docker.com/products/docker-desktop/) e Docker Compose

### Passos

1. **Clone o repositório:**
```bash
git clone https://github.com/chado-ma/schedule-core.git
cd schedule-core
```

2. **Inicie o banco de dados com Docker:**
```bash
docker-compose up -d
```

3. **Configure as variáveis de ambiente:**
- `spring.mail.username`: E-mail institucional do administrador (também usado para assinar tokens).
- `spring.mail.password`: Senha do e-mail.

4. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:3000`.

## 👨‍💻 Autor

  * **Gabriel Rezende Machado**

