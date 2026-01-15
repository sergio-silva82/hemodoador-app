# Hemodoador 🩸

Aplicação desenvolvida em **Spring Boot 3.4.1** com **Java 17**, voltada para gerenciamento de doadores de sangue.  
O projeto utiliza **Spring Web**, **Spring Data JPA**, **Spring Security**, autenticação via **JWT**, e persistência em banco de dados **MySQL**.

---

## 🚀 Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
  - Spring Web (APIs REST)
  - Spring Data JPA (persistência)
  - Spring Security (segurança/autenticação)
- **MySQL Connector 8.1.0**
- **JWT (JSON Web Token)** para autenticação
- **Lombok** para reduzir boilerplate de código

---

## 📦 Estrutura do Projeto

- `com.hemodoador.HemodoadorApplication` → Classe principal da aplicação
- `controller/` → Endpoints REST
- `service/` → Regras de negócio
- `repository/` → Interfaces JPA para acesso ao banco
- `model/` → Entidades JPA
- `security/` → Configurações de autenticação e JWT
- `dto/` → Objetos de transferência de dados

---

## ⚙️ Configuração do ambiente

### Pré-requisitos
- JDK 17+
- Maven 3.8+
- MySQL 8+

### Banco de Dados
Crie um banco de dados no MySQL:

```sql
CREATE DATABASE hemodoador;
```

rode os script conforme ordem do nome:

- V1__create_tables.sql
- V2__insert_usuario.sql
