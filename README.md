LiteraLuraAPI

Descrição

LiteraturaAPI é uma aplicação RESTful desenvolvida com Spring Boot, projetada para gerenciar livros e autores.
A API permite buscar livros por título usando uma API externa, listar livros e autores registrados no banco de dados, e realizar consultas por autores vivos em determinado ano ou livros em um idioma específico.

Funcionalidades

Buscar livro pelo título: Consulta um livro por título usando uma API externa e armazena os detalhes no banco de dados.

Listar livros registrados: Retorna todos os livros armazenados no banco de dados.

Listar autores registrados: Retorna todos os autores armazenados no banco de dados.

Listar autores vivos em determinado ano: Consulta autores que estavam vivos em um ano específico.

Listar livros por idioma: Retorna todos os livros em um idioma específico.

Tecnologias Utilizadas

Java: Linguagem de programação principal.

Spring Boot: Framework para criação da API RESTful.

Spring Data JPA: Facilita a interação com o banco de dados.

PostgreSQL: Banco de dados utilizado para armazenamento de informações.

Maven: Ferramenta de gerenciamento de dependências e build.

Estrutura do Projeto

Controller: Contém as classes responsáveis por receber e responder às requisições HTTP.

Service: Contém a lógica de negócio da aplicação.

Repository: Interface para comunicação com o banco de dados.

Model: Contém as classes de entidade que representam as tabelas no banco de dados.
