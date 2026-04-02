# Screen Sound

**Screen Sound** é um sistema de gerenciamento de playlists e mídias musicais desenvolvido em **Java** com **JPA/Hibernate**, como parte do projeto da disciplina de POO Avançado. O sistema simula funcionalidades básicas de serviços de streaming de música, permitindo:

- Cadastro de usuários  
- Criação e gerenciamento de playlists  
- Catálogo de mídias (músicas, podcasts e audiobooks)  
- Buscas por artista, título ou gênero  
- Cálculo automático da duração total das playlists  

![Java](https://img.shields.io/badge/Java-23%2B-blue)
![JPA](https://img.shields.io/badge/JPA-Hibernate-orange)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

---

## Tecnologias Utilizadas

- **Linguagem:** Java 23  
- **Persistência:** JPA/Hibernate  
- **Banco de Dados:** MySQL ou Qualquer banco compatível com JPA (configurável via `persistence.xml`)
- **Gerenciamento de Dependências:** Maven  
- **IDE Recomendada:** IntelliJ IDEA ou Eclipse  

---

## Funcionalidades Principais

### Usuários
- Cadastro com nome e e-mail  
- Login por e-mail  
- Associação de playlists ao usuário  

### Playlists
- Criação com nome personalizado  
- Adição/remoção de mídias  
- Cálculo automático da duração total  
- Listagem por usuário  

### Mídias
- **Tipos:** Música, Podcast e Audiobook  
- **Atributos:** Título, artista, duração e gênero  
- Catálogo geral organizado por tipo  
- Buscas por:
  - Título  
  - Artista  
  - Gênero musical  
  - Tipo de mídia

---

## Estrutura Orientada a Objetos

O sistema utiliza herança para representar os diferentes tipos de mídia:

- **Classe abstrata `Midia`**
  - Atributos comuns: `título`, `artista`, `duração`, `gênero`
- **Subclasses:**
  - `Musica`
  - `Podcast`
  - `Audiobook`

Cada tipo de mídia herda os atributos da classe `Midia` e pode ter comportamentos específicos, se necessário. Isso garante reutilização de código e facilita a manutenção e expansão do sistema.

---

# Como Executar

## Pré-requisitos:

- JDK 23 instalado

- Maven instalado

- Banco de dados configurado (ajustar persistence.xml)

---

## 🏁 Conclusão

Este projeto implementou com sucesso um **sistema completo de streaming musical**, demonstrando:

**Domínio de JPA/Hibernate**  
- Mapeamento ORM avançado com herança (`SINGLE_TABLE`)  
- Relacionamentos `@OneToMany` e `@ManyToMany` otimizados  
- Consultas JPQL eficientes  

**Padrões OO sólidos**  
- Hierarquia de classes bem definida (`Midia` como abstração)  
- Encapsulamento rigoroso  
- Polimorfismo em operações de catálogo  

**Boas práticas**  
- Tratamento de exceções  
- Separação de concerns (DAO vs Controller)  
- Documentação clara  

### Próximos Passos
- [ ] Adicionar autenticação JWT  
- [ ] Implementar API REST com Spring Boot  
- [ ] Desenvolver frontend em React/Angular  S

---
Projeto acadêmico concluído com excelência

Data de entrega: 19/05/2025.
Licença: MIT (livre para uso educacional).

---

## Diagrama de Classes (Core)

```mermaid
classDiagram
    direction TB
    
    class Midia {
        <<abstract>>
        +Long id
        +String titulo
        +String artista
        +Long duracao
        +Genero genero
    }
    
    class PlayList {
        +Long id
        +String nome
        +Long duracaoTotal
        +converterDuracao() String
    }
    
    class Usuario {
        +Long id
        +String nome
        +String email
    }
    
    Midia <|-- Musica
    Midia <|-- Podcast
    Midia <|-- Audiobook
    
    Usuario "1" --> "n" PlayList
    PlayList "n" --> "n" Midia
