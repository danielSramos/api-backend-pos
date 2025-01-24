# API de Gerenciamento de Usuários e Posts

Bem-vindo à API de Gerenciamento de Usuários e Posts! Este projeto foi desenvolvido com o objetivo de demonstrar um CRUD completo para as entidades `User` e `Post`, utilizando tecnologias modernas para facilitar a integração e o uso.

---

## 📝 Descrição

Essa API permite gerenciar usuários e seus respectivos posts. Entre as principais funcionalidades estão:
- Criação, listagem, atualização e exclusão de usuários.
- Gerenciamento de posts relacionados a cada usuário.
- Validações integradas para garantir consistência nos dados.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot 3.4.1**: Framework para simplificar o desenvolvimento de aplicações Java.
- **Banco de dados H2**: Banco de dados em memória para fins de teste e desenvolvimento.
- **Maven**: Ferramenta de gerenciamento de dependências e build.

---

## ⚙️ Configuração do Ambiente

Siga os passos abaixo para configurar e rodar o projeto em um ambiente novo:

### 1️⃣ Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas no seu sistema:
- **Java 17** ou superior ([Adoptium Temurin](https://adoptium.net/))
- **Maven** ([Como instalar](https://maven.apache.org/install.html))
- **Git** (opcional, mas recomendado)

### 2️⃣ Clone o repositório

Execute o comando abaixo para clonar o repositório em sua máquina local:
```bash
git clone https://github.com/danielSramos/api-backend-pos.git
```

### 3️⃣ Navegue até a pasta do projeto

cd api-backend-pos

### 4️⃣ Instale as dependências do projeto

Com o Maven configurado, instale as dependências necessárias:
```bash
mvn clean install
```

### 5️⃣ Execute o projeto

Inicie o servidor Spring Boot com o comando:
```bash
mvn spring-boot:run
```

## 📂 Configuração do Banco de Dados
Por padrão, o projeto utiliza o banco de dados H2 em memória. Para acessá-lo, siga os passos abaixo:

- Acesse o console do H2 em: http://localhost:8080/h2-console.
- Use as configurações padrão:
- JDBC URL: jdbc:h2:mem:testdb
- User: sa
- Password: (deixe em branco)

#### No projeto existe um arquivo chamado db.sql que possui uma consulta para adicionar alguns dados para teste.

---

## 🛠️ Endpoints

### Usuários
- GET /users: Lista todos os usuários.
- POST /users: Cria um novo usuário.
- GET /users/{id}: Busca um usuário pelo ID.
- PUT /users/{id}: Atualiza os dados de um usuário.
- DELETE /users/{id}: Remove um usuário.

### Posts
- GET /posts: Lista todos os posts.
- POST /posts: Cria um novo post.
- GET /posts/{id}: Busca um post pelo ID.
- PUT /posts/{id}: Atualiza os dados de um post.
- DELETE /posts/{id}: Remove um post.

---

## Estrutura do projeto

```
src/
├── main/
│   ├── java/
│   │   └── br.com.unifap.pos.atv_sinc1/  # Pacote principal
│   │       ├── controller/              # Controladores da API
│   │       ├── model/                   # Entidades do banco
│   │       ├── repository/              # Interfaces de repositórios
│   │       └── service/                 # Lógica de negócios
│   └── resources/
│       ├── application.yml              # Configurações do Spring Boot
│       └── data.sql                     # Dados iniciais para o banco H2
└── test/
└── java/                            # Testes unitários e de integração
```

---

## 📄 Documentação

A API segue os padrões REST e pode ser facilmente testada utilizando ferramentas como:

- Postman
- Insomnia
- cURL

---

## 📬 Contato

Criado por @danielSramos. Fique à vontade para contribuir ou entrar em contato caso tenha dúvidas!
