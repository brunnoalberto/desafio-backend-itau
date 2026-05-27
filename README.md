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