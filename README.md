# PrevIA API — Backend · Sprint 4 · Entrega Final

API REST em Spring Boot do sistema **PrevIA**, consumida pelo app Expo / React Native.

## 1. Projeto e problema

Em ambientes industriais, a segurança exige monitoramento contínuo e resposta rápida para evitar acidentes e proteger os operadores. Muitos riscos, como a ausência de EPI (capacete, colete, luvas), posturas inadequadas ou aproximação de áreas perigosas, só são percebidos quando o incidente já ocorreu.

O **PrevIA** registra **alertas** de segurança identificados no ambiente industrial. Esta API é responsável por **armazenar, consultar e gerenciar** esses alertas, permitindo que o app mobile os exiba e cadastre novos.

**Entidade principal:** `Alerta`

| Campo | Descrição |
|---|---|
| `id` | Gerado pelo backend |
| `tipo` | Tipo do alerta (ex.: ausência de EPI, comportamento de risco) |
| `localizacao` | Onde ocorreu |
| `nivelRisco` | Nível de risco |
| `descricao` | Descrição do ocorrido |

## 2. Integrantes

| Nome | RM |
|---|---|
| Agatha Cassari Benedicto | 556251 |
| Gustavo Shinn Shyong Cheng | 559084 |
| Leonardo Fernandes Veleiro | 557773 |
| Sara Barbosa da Silva | 559042 |

## 3. Repositórios

| Parte | Link |
|---|---|
| Backend (Spring Boot): este repositório | https://github.com/AgathaCassari/Sprint1-backend---Advanced-Programming-Mobile-Dev |
| Frontend (Expo / React Native) | https://github.com/AgathaCassari/Sprint2-frontend---Advanced-Programming-Mobile-Dev |

## 4. Arquitetura (camadas)

O backend segue o padrão de camadas:

```
Controller  →  Service  →  Repository  →  Banco H2 (arquivo)
(HTTP/JSON)    (regras)     (JPA)          ./data/previa
```

- **model**: entidade `Alerta`
- **repository**: acesso ao banco (Spring Data JPA)
- **service**: regras de negócio
- **controller**: `AlertaController`, com os endpoints REST e `@CrossOrigin(origins = "*")`

## 5. Pré-requisitos

- **Java 17**
- **Maven 3.9+** (o repositório também traz o wrapper `mvnw`, então o Maven instalado é opcional)
- Porta **8080** livre

Para conferir: `java -version` (deve mostrar 17) e `mvn -version`.

## 6. Como subir o backend (porta 8080)

```bash
git clone https://github.com/AgathaCassari/Sprint1-backend---Advanced-Programming-Mobile-Dev.git
cd Sprint1-backend---Advanced-Programming-Mobile-Dev
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run   (ou: mvn spring-boot:run)
```

Alternativa: rodar a classe principal `PreviaApiApplication.java` pela IDE (VS Code, IntelliJ).

Quando subir, a API fica em **`http://localhost:8080`**.

Confirme que está no ar abrindo no navegador:

```
http://localhost:8080/alertas
```

Deve aparecer um JSON (`[]` ou a lista de alertas).

## 7. Banco de dados (H2 em arquivo)

- URL JDBC: `jdbc:h2:file:./data/previa`
- Usuário: `sa` · Senha: em branco
- Console H2 (opcional): `http://localhost:8080/h2-console`
- Os dados **persistem** entre reinicializações (arquivo `data/previa.mv.db`). Não é banco em memória.
- Configuração em `application.properties`.

## 8. CORS

O `AlertaController` usa `@CrossOrigin(origins = "*")`, o que libera o acesso do app (que roda em outra origem) à API.

## 9. Endpoints

| Ação | Método | Rota |
|---|---|---|
| Listar alertas | GET | `/alertas` |
| Buscar por ID | GET | `/alertas/{id}` |
| Criar alerta | POST | `/alertas` |
| Atualizar (opcional) | PUT | `/alertas/{id}` |
| Remover (opcional) | DELETE | `/alertas/{id}` |

Exemplo de corpo do `POST /alertas`:

```json
{
  "tipo": "Ausência de EPI",
  "localizacao": "Setor A",
  "nivelRisco": "Alto",
  "descricao": "Funcionário sem capacete na área de carga"
}
```

Teste rápido pelo terminal:

```bash
curl http://localhost:8080/alertas
curl -X POST http://localhost:8080/alertas -H "Content-Type: application/json" \
  -d '{"tipo":"Ausência de EPI","localizacao":"Setor A","nivelRisco":"Alto","descricao":"Sem capacete"}'
```

## 10. Como testar a integração com o app

1. Suba o backend e confirme `GET http://localhost:8080/alertas` retornando JSON.
2. Suba o app (veja o README do frontend) e abra a tela de **lista**: ela deve mostrar os mesmos dados.
3. Cadastre um alerta pelo app e confira que ele aparece:
   - na lista do app;
   - em `GET /alertas` no navegador ou Postman;
   - no arquivo `data/previa.mv.db` (dado persistido, não é mock).
4. Para simular falha, **pare o backend** (`Ctrl + C` no terminal) e recarregue a lista no app: ele deve mostrar o estado de erro, sem dados.
