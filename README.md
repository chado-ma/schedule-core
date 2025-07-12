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

## 🛠️ Tecnologias Utilizadas

  * **Backend:** Java 17, Spring Boot 3.5.0
  * **Banco de Dados:** PostgreSQL
  * **Gerenciador de Dependências:** Maven
  * **Containerização:** Docker (via `docker-compose.yml`)

## 🏗️ Arquitetura: Ports and Adapters (Hexagonal)

O projeto foi estruturado seguindo o padrão de **Arquitetura Hexagonal**, que isola a lógica de negócio de dependências externas e facilita a manutenção e testabilidade do sistema.

  * **Hexágono (Core do Domínio):**

      * Localizado no pacote `domains`, contém a lógica de negócio pura e agnóstica a tecnologias.
      * **`ports`**: Define as interfaces (Portas) que o domínio usa para se comunicar com o mundo exterior, como `DatabasePort` e `EmailSenderPort`. O domínio não sabe qual tecnologia implementará essas portas.

  * **Adaptadores (Infrastructure):**

      * O pacote `infrasctructure` contém os Adaptadores, que são as implementações concretas das portas.
      * **Adaptadores Inbound (Driving):** Recebem comandos do mundo exterior e os enviam para o domínio. Ex: `ScheduleController` é um adaptador que expõe a lógica via API REST.
      * **Adaptadores Outbound (Driven):** São controlados pelo domínio e fornecem implementações para as portas. Ex: `DatabaseAdapter` implementa o `DatabasePort` usando Spring Data JPA e PostgreSQL.

Essa abordagem, inspirada no Domain-Driven Design (DDD), garante que o coração da aplicação (`domains`) permaneça isolado e testável, independentemente das tecnologias de infraestrutura.

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

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/chado-ma/schedule-core.git
    cd schedule-core
    ```

2.  **Inicie o banco de dados com Docker:**
    O banco de dados PostgreSQL pode ser iniciado facilmente com o Docker Compose.

    ```bash
    docker-compose up -d
    ```

    Isso iniciará um container com o PostgreSQL na porta `5432`, com as credenciais definidas no arquivo `docker-compose.yml`.

3.  **Configure as variáveis de ambiente:**
    A aplicação utiliza variáveis de ambiente para as credenciais do e-mail de notificação. Configure-as no seu ambiente ou no arquivo `application.yml`.

      * `email`: E-mail usado para enviar notificações.
      * `senha`: Senha do e-mail.

4.  **Execute a aplicação:**
    Utilize o Maven para compilar e iniciar o servidor Spring Boot.

    ```bash
    mvn spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:3000`.

## 👨‍💻 Autor

  * **Gabriel Rezende Machado**
