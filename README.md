# Schedule-Core UFU (Backend)

## 🎯 Sobre o Projeto
**Schedule-Core** é o servidor backend da plataforma de **agendamento de espaços esportivos** da UFU.
Centraliza a consulta de horários, criação/cancelamento de reservas e administração de ginásios, reduzindo trabalho manual e conflitos de agenda.
Porta padrão da API: **3000**.

---

## ✨ Funcionalidades
- **Gestão de agendamentos**: criar, listar por data/usuário e cancelar.
- **Administração**: CRUD de ginásios e restrições (feriados/eventos).
- **Validações de negócio** (Chain of Responsibility): evita duplicidade, respeita restrições e horários válidos.
- **Notificações por e-mail** (ex.: cancelamento).

---

## 🏗️ Arquitetura (Hexagonal / Ports & Adapters)
- **Domínio (`domains/`)**: regras de negócio e **ports** (interfaces) limpas.
- **Infrastructure (`infrasctructure/`)**: adapters REST, banco, e-mail, segurança.

Principais pacotes:
- **Controllers** (`infrasctructure/api/v1`), **Auth** (JWT), **Database** (JPA + Postgres), **EmailSender**, **ErrorHandler**.

---

## 🔐 Autenticação & Autorização (JWT + Spring Security)
- **Fluxo**: login por e-mail institucional → código → geração de **JWT** com claims (`matricula`, `email`, `name`, `role`) e validade de ~2h.
- **Assinatura**: HMAC derivado de **SHA-256 do e-mail institucional**.

### Proteção de rotas
- Público: `/v3/api-docs/**`, `/swagger-ui/**`, `/swagger-ui.html`, e endpoints de auth.
- Autenticado: `/v1/schedule/**`
- Somente **ADMIN**: `/v1/adm/**`

### Endpoints de Autenticação

| Método | Rota           | Descrição                                         |
|--------|----------------|---------------------------------------------------|
| POST   | `/auth/email`  | Envia código de verificação para e-mail `@ufu.br`.|
| POST   | `/auth/verify` | Valida o código e retorna JWT.                    |
| POST   | `/auth/token`  | Renovação de token para e-mail já registrado.     |
| GET    | `/auth/user`   | Dados do usuário autenticado (via Bearer).        |

**Exemplo de fluxo:**
```json
POST /auth/email
{ "email": "usuario@ufu.br" }

POST /auth/verify
{ "email": "usuario@ufu.br", "codigo": "123456" }
```

---

## 🗺️ API Endpoints principais (/v1)

### Agendamentos (`/v1/schedule`)

| Método | Rota                | Descrição                                 |
|--------|---------------------|-------------------------------------------|
| GET    | /schedule           | Busca horários disponíveis/agendados por data. |
| POST   | /schedule           | Cria novo agendamento.                    |
| POST   | /schedule/delete    | Cancela um agendamento.                   |
| GET    | /schedule/{matricula}| Lista agendamentos do usuário.           |

### Administração (`/v1/adm`)

| Método | Rota                  | Descrição                        |
|--------|-----------------------|----------------------------------|
| POST   | /ginasio              | Cria/atualiza ginásio.           |
| GET    | /ginasio              | Lista ginásios.                  |
| POST   | /ginasio/delete/{id}  | Remove ginásio.                  |
| POST   | /restricao            | Cria restrição de data por ginásio. |
| GET    | /restricao            | Lista restrições.                |
| GET    | /schedule             | Lista todos os agendamentos.     |

---

## ⚙️ Configuração & Execução

### Pré-requisitos

- Java 17+
- Maven
- Docker + Docker Compose

### Variáveis/Propriedades de E-mail

- `spring.mail.username` (obrigatório): e-mail institucional (define From, seed do JWT e o ADMIN inicial).
- `spring.mail.password`: senha.
- SMTP: `smtp.office365.com:587` (TLS).

### Perfis de Execução

**localdb** (Postgres local na 5432):
`spring.datasource.url=jdbc:postgresql://localhost:5432/SCHEDULEDB` (user/pass: user/user)

**docker** (Postgres do docker-compose na 5555):
`spring.datasource.url=jdbc:postgresql://localhost:5555/SCHEDULEDB` (user/pass: user/user)

A API roda por padrão na porta 3000.

#### Subindo tudo com Docker (recomendado)
```sh
docker-compose up -d
mvn spring-boot:run -Dspring-boot.run.profiles=docker
```

#### Execução local (banco próprio)
```sh
# 1) Garanta Postgres local rodando (SCHEDULEDB / user:user)
# 2) Rode a aplicação apontando para o perfil localdb
mvn spring-boot:run -Dspring-boot.run.profiles=localdb
```

---

## 📘 Documentação interativa (Swagger/OpenAPI)

- Swagger UI: `/swagger-ui/index.html`
- OpenAPI JSON: `/v3/api-docs`

---

## 🧪 Testes manuais (Insomnia)

Há uma collection do Insomnia pronta em `src/test/Insomnia_2025-07-21.yaml` com requests de todos os endpoints.

Dica: a collection usa `localhost:3000` nas URLs.

---

## 🧱 Persistência (JPA / Postgres)

Entidades principais: User, Ginasio, Restricao, Reserva.

---

## 📨 Envio de E-mail

`EmailSenderAdapter` usa `JavaMailSender` (From = `spring.mail.username`) para enviar código de autenticação e notificação de cancelamento.

---

## 🛡️ Tratamento de Erros

`ErrorHandler` (`ControllerAdvice`) padroniza respostas para 400 e 401, além de log estruturado.

---

## 📂 Estrutura do Repositório (resumo)

- `src/main/java/com/schedulecore/ufu/...` – domínio, adapters (API/Auth/DB/Email), config de segurança e error handler.
- `src/main/resources/` – application.yml, perfis application-localdb.yml e application-docker.yml.
- `src/test/Insomnia_2025-07-21.yaml` – collection Insomnia.

---

## 👤 Autor

Gabriel Rezende Machado
- LinkedIn: [https://www.linkedin.com/in/gabriel-rezende-machado-920b18183/](https://www.linkedin.com/in/gabriel-rezende-machado-920b18183/)