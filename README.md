# Screen Sound 🎵

**Screen Sound** é um sistema de gerenciamento de playlists e mídias musicais desenvolvido em **Java** com **JPA/Hibernate**, como parte do projeto da disciplina de POO Avançado. O sistema simula funcionalidades básicas de serviços de streaming de música, permitindo:

- Cadastro de usuários  
- Criação e gerenciamento de playlists  
- Catálogo de mídias (músicas, podcasts e audiobooks)  
- Buscas por artista, título ou gênero  
- Cálculo automático da duração total das playlists  

![Java](https://img.shields.io/badge/Java-23%2B-blue)![JPA](https://img.shields.io/badge/JPA-Hibernate-orange)![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

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

## Como Executar

###Pré-requisitos:

- JDK 23 instalado

- Maven instalado

- Banco de dados configurado (ajustar persistence.xml)

---

