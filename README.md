# PrevIA - API de Monitoramento Proativo Industrial

## Descrição da API

A API PrevIA foi desenvolvida para representar o backend de um sistema inteligente de monitoramento de segurança industrial em tempo real.

O sistema tem como objetivo registrar e gerenciar alertas relacionados a situações de risco dentro do ambiente industrial, como ausência de EPIs e comportamentos perigosos detectados pelo sistema de monitoramento.

A aplicação foi desenvolvida utilizando Spring Boot seguindo o padrão arquitetural em camadas:
- Model
- Repository
- Service
- Controller

---

## Entidade Escolhida

### Alerta

A entidade Alerta representa ocorrências de risco identificadas no ambiente industrial.

Campos da entidade:

| Campo | Tipo |
|---|---|
| id | Long |
| tipo | String |
| localizacao | String |
| nivelRisco | String |
| descricao | String |

---

## Endpoints Disponíveis

### Criar alerta
POST /alertas

### Listar alertas
GET /alertas

### Buscar alerta por ID
GET /alertas/{id}

### Atualizar alerta
PUT /alertas/{id}

### Deletar alerta
DELETE /alertas/{id}

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- Banco H2

---

## Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone LINK_DO_REPOSITORIO
```

### 2. Abrir no VS Code

Abrir a pasta do projeto no Visual Studio Code.

### 3. Executar a aplicação

Rodar a classe principal:

```text
PreviaApiApplication.java
```

### 4. Acessar a API

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

## Console H2

Acesso:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:file:./data/previa
```

Usuário:

```text
sa
```

Senha:
(vazia)