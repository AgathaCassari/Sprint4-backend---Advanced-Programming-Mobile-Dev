# PrevIA API — Backend (Sprint 3)

## Descrição

API REST em Spring Boot para o sistema PrevIA. A entidade do grupo é
**Alerta** (ausência de EPI, comportamento de risco etc.). Nesta sprint, o
backend segue o padrão de camadas da Sprint 1 (model, repository, service,
controller) e passa a ser consumido de verdade pelo app Expo/React Native,
que está em um repositório separado: **[nome/link do repo do frontend]**.

## Como subir o backend

1. Clone este repositório.
2. Rode a aplicação pela classe principal `PreviaApiApplication.java`
   (pelo VS Code/IDE, ou `./mvnw spring-boot:run`).
3. A API sobe em `http://localhost:8080`.
4. Console H2 (opcional, para inspecionar os dados):
   `http://localhost:8080/h2-console`
   JDBC URL: `jdbc:h2:file:./data/previa` — usuário `sa`, senha em branco.
5. Confirme que os endpoints respondem em JSON antes de abrir o app — por
   exemplo, acessando `http://localhost:8080/alertas` no navegador ou
   testando no Postman/Insomnia.

O `AlertaController` tem `@CrossOrigin(origins = "*")`, liberando o acesso do
app (que roda em outra origem) para a API.

## Endpoints

| Ação                  | Método | Rota            |
| --------------------- | ------ | --------------- |
| Listar alertas         | GET    | `/alertas`      |
| Buscar por ID          | GET    | `/alertas/{id}` |
| Criar alerta           | POST   | `/alertas`      |
| Atualizar (opcional)   | PUT    | `/alertas/{id}` |
| Remover (opcional)     | DELETE | `/alertas/{id}` |

Campos do `Alerta`: `id` (gerado pelo backend), `tipo`, `localizacao`,
`nivelRisco`, `descricao`.

## Como testar a integração

1. Suba o backend e confirme `GET http://localhost:8080/alertas` retornando
   `[]` ou os alertas já salvos no H2.
2. Suba o app do repositório do frontend e abra a tela de lista — ela deve
   carregar os mesmos dados.
3. Cadastre um alerta pelo app e confira que ele aparece:
   - na lista do app;
   - no `GET /alertas` direto no navegador/Postman;
   - no arquivo `data/previa.mv.db` (dado persistido de verdade, não é mock).
4. Para simular falha, pare o backend e reabra a tela de lista no app — ela
   deve mostrar o estado de erro.

## Persistência

Banco H2 em modo arquivo (`jdbc:h2:file:./data/previa`), configurado em
`application.properties`. Os dados sobrevivem a reinicializações da
aplicação (não é um banco em memória).
