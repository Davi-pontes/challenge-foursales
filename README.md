# Challenge Foursales

## Descrição
O **Challenge Foursales** é um projeto Spring Boot desenvolvido em Java 17 para gerenciamento de pedidos e produtos em um e-commerce. O projeto foi construído utilizando Maven para gerenciamento de dependências e IntelliJ IDEA como IDE principal. Ele inclui APIs RESTful, autenticação segura com JWT e OAuth2, CRUD de produtos e controle de permissões de usuários.

## Características
- **APIs RESTful**: Serviços RESTful implementados para gerenciamento de produtos e pedidos.
- **MySQL**: Banco de dados relacional para armazenamento eficiente de dados.
- **Spring Security**: Integração com autenticação baseada em JWT (JSON Web Tokens) e OAuth2 para acesso seguro à API.
- **Lombok**: Simplificação do processo de desenvolvimento com anotações que reduzem o código boilerplate.
- **JPA & Hibernate**: Persistência de dados com Java Persistence API (JPA) e Hibernate para mapeamento objeto-relacional (ORM).
- **OAuth2**: Autenticação e autorização via OAuth2 para integração com provedores de terceiros (Google, Facebook, etc.) e gerenciamento de tokens de acesso.

## Principais tecnologias e ferramentas
- **Spring Boot**: Framework principal para construção da aplicação.
- **MySQL**: Banco de dados relacional.
- **Spring Security & JWT**: Proteção dos endpoints da API com JSON Web Tokens.
- **OAuth2**: Autenticação e autorização via OAuth2.
- **Lombok**: Redução de código boilerplate.
- **JPA & Hibernate**: Persistência e mapeamento objeto-relacional.

## Instalação e Configuração

### Clone o repositório
```bash
git clone https://github.com/Davi-pontes/challenge-foursales.git
cd challenge-foursales
```

### Configure as variáveis de ambiente
Na raiz do projeto defina as credenciais do banco de dados:

```bash
DB_URL=<sua-url-do-banco>
DB_USER=<seu-usuario>
DB_PASSWORD=<sua-senha>
```

### Configuração das Chaves de Criptografia
O projeto utiliza criptografia assimétrica, portanto, gere as chaves pública e privada na pasta resources com os comandos:

```bash
cd src/main/resources
openssl genrsa -out app.key 2048
openssl rsa -in app.key -pubout -out app.pub
```

## Inicializar projeto
```bash
mvn clean install
mvn spring-boot:run
```
A API estará disponível em: http://localhost:8080
