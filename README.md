# Sistema de Consultas — API Backend (Sprint 4)

🎥 Vídeo de demonstração (YouTube, Não listado): <https://www.youtube.com/watch?v=CWpSwfw5hJQ>

## Descrição

API REST em Spring Boot para o Sistema de Consultas. As entidades do grupo são **Consulta**, **Médico**, **Paciente** e **Especialidade**. O backend segue o padrão de camadas (model, repository, service, controller) e é consumido de verdade pelo app Expo/React Native, que está em um repositório separado: **https://github.com/AgathaCassari/Sprint4-frontend---Advanced-Programming-Mobile-Dev**.

**Problema:** pacientes têm dificuldade para agendar consultas e acompanhar seus atendimentos, e os médicos precisam controlar as consultas marcadas na sua agenda. O sistema centraliza esse processo: o paciente escolhe a especialidade e o médico e agenda a consulta; o médico consulta e gerencia (confirma ou cancela) as consultas agendadas, com todos os dados persistidos na API.

**Entidade principal:** `Consulta` (`id`, `medico`, `paciente`, `dataHora`, `status`, `valor`, `observacoes`), que se relaciona com `Medico`, `Paciente` e `Especialidade`.

## Integrantes

| Nome | RM |
|---|---|
| Agatha Cassari Benedicto | 556251 |
| Gustavo Shinn Shyong Cheng | 559084 |
| Leonardo Fernandes Veleiro | 557773 |
| Sara Barbosa da Silva | 559042 |

## Repositórios

- Backend (este repositório): https://github.com/AgathaCassari/Sprint4-backend---Advanced-Programming-Mobile-Dev
- Frontend: https://github.com/AgathaCassari/Sprint4-frontend---Advanced-Programming-Mobile-Dev

## Pré-requisitos

- Java 17
- Maven (ou o wrapper `./mvnw`, que já vem no repositório)
- Porta 8080 livre

## Como subir o backend

1. Clone este repositório.
2. Rode a aplicação pela classe principal `BackendConsultasApplication.java` (pelo VS Code/IDE, ou `./mvnw spring-boot:run`; no Windows, `mvnw.cmd spring-boot:run`).
3. A API sobe em `http://localhost:8080`.
4. Console H2 (opcional, para inspecionar os dados):
`http://localhost:8080/h2-console` — JDBC URL: `jdbc:h2:file:./data/consultas` — usuário e senha conforme `application.properties`.
5. Confirme que os endpoints respondem em JSON antes de abrir o app — por exemplo, acessando `http://localhost:8080/consultas` no navegador ou testando no Postman/Insomnia.

O CORS está configurado (pacote `config`), liberando o acesso do app (que roda em outra origem) para a API.

## Endpoints

| Recurso | Ação | Método | Rota |
| --- | --- | --- | --- |
| Health | Verificar se a API está no ar | GET | `/health` |
| Especialidades | Listar | GET | `/especialidades` |
| | Buscar por ID | GET | `/especialidades/{id}` |
| | Criar | POST | `/especialidades` |
| Médicos | Listar | GET | `/medicos` |
| | Buscar por ID | GET | `/medicos/{id}` |
| | Buscar por CRM | GET | `/medicos/crm/{crm}` |
| | Listar por especialidade | GET | `/medicos/especialidade/{especialidadeId}` |
| | Cadastrar | POST | `/medicos` |
| | Atualizar | PUT | `/medicos/{id}` |
| Pacientes | Listar | GET | `/pacientes` |
| | Buscar por ID | GET | `/pacientes/{id}` |
| | Buscar por CPF | GET | `/pacientes/cpf/{cpf}` |
| | Cadastrar | POST | `/pacientes` |
| Consultas | Listar | GET | `/consultas` |
| | Buscar por ID | GET | `/consultas/{id}` |
| | Agendar | POST | `/consultas` |
| | Atualizar (confirmar/cancelar) | PUT | `/consultas/{id}` |
| | Listar por médico | GET | `/consultas/medico/{medicoId}` |
| | Listar por paciente | GET | `/consultas/paciente/{pacienteId}` |

Campos da `Consulta`: `id` (gerado pelo backend), `medico` (objeto com `id`), `paciente` (objeto com `id`), `dataHora`, `status`, `valor`, `observacoes`.

## Como testar a integração

1. Suba o backend e confirme `GET http://localhost:8080/consultas` retornando `[]` ou as consultas já salvas no H2.
2. Suba o app do repositório do frontend e abra as telas de escolha de especialidade e médico — elas devem carregar os mesmos dados da API.
3. Agende uma consulta pelo app e confira que ela aparece:
  - na lista do app;
  - no `GET /consultas` direto no navegador/Postman;
  - no arquivo do banco `data/consultas.mv.db` (dado persistido de verdade, não é mock).
4. Para simular falha, pare o backend (`Ctrl + C`) e reabra a tela no app — ela deve mostrar o estado de erro.

## Persistência

Banco H2 em modo arquivo (`jdbc:h2:file:./data/consultas`), configurado em `application.properties`. Os dados sobrevivem a reinicializações da aplicação (não é um banco em memória). Na primeira execução, com o banco vazio, o `DataLoader` cadastra dados iniciais; nas próximas ele detecta que já existem e pula a carga. Há índices únicos para CRM, CPF, e-mail e nome da especialidade.

## Vídeo

🎥 <https://www.youtube.com/watch?v=CWpSwfw5hJQ> (YouTube, Não listado)
O vídeo mostra: README e repositórios → backend no ar com GET em JSON → app listando a API → cadastro no app e o dado no backend → backend parado → app recarregado sem dados (erro/loading, sem mock).
