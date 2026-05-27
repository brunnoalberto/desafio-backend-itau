# Desafio Itaú Unibanco - API de Transações e Estatísticas

Este repositório contém o desafio técnico para a vaga de engenharia de software backend do Itaú Unibanco. O objetivo é construir uma API REST que receba transações e calcule estatísticas agregadas em tempo real com base nos dados dos últimos 60 segundos.

---

## 📋 Requisitos do Desafio

A API deve ser capaz de operar sob alta carga e concorrência, realizando todas as operações estritamente em memória (sem persistência em banco de dados ou arquivos).

Os critérios de aceite do desafio são divididos em três endpoints principais:

### 1. Receber Transação (`POST /transacao`)
Recebe um payload contendo o valor e a data/hora em que a transação ocorreu.

* **Regras de Validação:**
    * O JSON deve ser sintaticamente válido.
    * O campo `valor` deve ser maior ou igual a zero (>= 0).
    * O campo `dataHora` não pode estar no futuro.
* **Critérios de Resposta:**
    * `201 Created`: Transação recebida com sucesso e válida.
    * `422 Unprocessable Entity`: Requisição aceita sintaticamente, mas que violou algum critério de validação (ex: data no futuro ou valor negativo).
    * `400 Bad Request`: JSON malformado ou inválido.
    * **Nota:** As respostas de erro não devem conter nenhum corpo (body).

### 2. Limpar Transações (`DELETE /transacao`)
Limpa todos os dados de transações que estão armazenados em memória.

* **Critério de Resposta:**
    * `200 OK`: Dados apagados com sucesso.

### 3. Calcular Estatísticas (`GET /estatistica`)
Calcula e retorna as métricas agregadas das transações que aconteceram **exclusivamente nos últimos 60 segundos** (em relação ao momento atual do sistema).

* **Formato de Retorno (`200 OK`):**
```json
  {
    "count": 10,
    "sum": 1234.50,
    "avg": 123.45,
    "min": 10.00,
    "max": 500.00
  }
```

---

# Desafios Extras e Diferenciais Técnicos

A seguir, estão listados alguns desafios extras para elevar o nível da solução, testar os limites da aplicação e demonstrar boas práticas de engenharia de software e arquitetura.

---

## 🎯 Lista de Desafios Extras

- [ ] **Testes Automatizados**
    * Implementar testes unitários e/ou funcionais integrados.
    * *Foco na efetividade:* Garantir a cobertura não apenas do "caminho feliz", mas também de cenários de exceção, dados inválidos e concorrência.

- [ ] **Containerização**
    * Criar os arquivos necessários (`Dockerfile` e/ou `docker-compose.yml`) para disponibilizar e rodar a aplicação isolada dentro de um container. *(Não é necessário publicar a imagem).*

- [ ] **Logs**
    * Estruturar a aplicação para reportar informações relevantes sobre o que está acontecendo em tempo de execução. Rastreabilidade essencial para o diagnóstico de problemas na aplicação trabalhando sob carga.

- [X] **Observabilidade**
    * Disponibilizar endpoint para a verificação de saúde do sistema (*healthcheck*).

- [ ] **Performance & Benchmarking**
    * Estimar, medir e documentar o tempo exato de computação que a aplicação gasta para calcular e consolidar as estatísticas.

- [X] **Tratamento de Erros Customizado**
    * Explorar os recursos do Spring Boot para interceptar e customizar os retornos de erro padrão da API, devolvendo respostas semânticas e limpas para o cliente.

- [ ] **Documentação da API**
    * Documentar formalmente os contratos de entrada e saída dos endpoints da API utilizando ferramentas e padrões de mercado (ex: OpenAPI / Swagger).

- [ ] **Documentação do Sistema**
    * Escrever um guia claro e objetivo explicando o passo a passo para que outra pessoa desenvolvedora consiga buildar (construir) e executar a aplicação localmente pela primeira vez.

- [ ] **Configurações Dinâmicas**
    * Tornar a janela de tempo do cálculo de estatísticas totalmente configurável. O padrão deve ser 60 segundos, mas o sistema deve aceitar outros valores (como 120 segundos) sem necessidade de alterar o código-fonte.