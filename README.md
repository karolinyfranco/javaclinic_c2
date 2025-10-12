# 🏥 JavaClinic — Sistema de Gerenciamento de Consultas Médicas

## Sobre o Projeto 

O JavaClinic é um sistema de gerenciamento de consultas médicas desenvolvido em Java, com persistência de dados em MySQL e estrutura organizada segundo boas práticas de separação em pacotes.

O objetivo do projeto é simular um sistema de clínica médica, permitindo o cadastro de médicos e pacientes, e o agendamento de consultas, com base em um banco relacional.

Este projeto foi desenvolvido como parte da disciplina de Banco de Dados, com Java + MySQL utilizando Docker e Maven.


## Funcionalidades

- Cadastro, atualização, listagem e exclusão de Médicos
- Cadastro, atualização, listagem e exclusão de Pacientes
- Agendamento e cancelamento de Consultas


## Tecnologias Utilizadas

- **Linguagem:** Java 21+
- **Gerenciador de Dependências:** Maven
- **Banco de Dados:** MySQL 8
- **Ambiente de Banco:** Docker
- **Interface de Gerenciamento do Banco:** phpMyAdmin
- **Driver JDBC:** MySQL Connector/J


## Estrutura de Pastas

```
javaclinic_c2/
├── src/
│   ├── main/java/br/faesa/javaclinic/
│        ├── model/              # Classes de entidade
│        ├── controller/         # Controle das operações CRUD
│        ├── conexion/           # Conexão com o banco via JDBC
│        ├── reports/            # Geração de relatórios
│        ├── utils/              # Utilitários gerais
│        └── principal/          # Ponto de entrada da aplicação
│
├── diagrams/                    # Diagramas relacionais do projeto
│
├── sql/                         # Scripts SQL de criação e inserção de dados  
│   ├── create_tables_javaclinic.sql
│   ├── insert_samples_records.sql
│   └── insert_samples_related_records.sql
│
├── mysql_data/                 # Dados persistentes do banco (criado pelo Docker)
├── docker-compose.yml          # Configuração dos containers MySQL e phpMyAdmin
├── pom.xml                     # Configurações Maven e dependências
└── README.md
```

## Configurando o Banco de Dados com Docker

O projeto já inclui um arquivo docker-compose.yml para subir o ambiente completo do banco de dados.

### 1️⃣ Subir o ambiente

No terminal, dentro da pasta do projeto:

```bash
  docker compose up -d
```
Isso criará:
- Um container MySQL (porta 3307)
- Um container phpMyAdmin (porta 8080)

### 2️⃣ Acessar o phpMyAdmin

Abra no navegador: http://localhost:8080

### 3️⃣ Scripts SQL
Os scripts estão em /sql e são automaticamente executados na primeira vez que o container é criado:

- create_tables_javaclinic.sql — Criação do banco e tabelas
- insert_samples_records.sql — Dados iniciais de médicos e pacientes
- insert_samples_related_records.sql — Inserção de consultas


Se precisar recriar o banco do zero:
```bash
docker compose down -v
docker compose up -d
```


## Dependências (Maven)

No arquivo pom.xml, a dependência do MySQL Connector/J foi adicionada:

```
<dependencies>
    <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.3.0</version>
     </dependency>
</dependencies>
```
## Autores

Desenvolvido por alunos do curso de Ciência da Computação — FAESA, para a disciplina de Banco de Dados (C2).

- Ana Luiza Menelli Taylor: [@analuizataylor](https://github.com/analuizataylor)
- Felipe Valério Rocha: [@sabugoestrela](https://github.com/sabugoestrela)
- Gustavo Rissoli Vicente: [@GustavoRissoli](https://github.com/GustavoRissoli)
- Karoliny Vicente Franco: [@karolinyfranco](https://github.com/karolinyfranco)
