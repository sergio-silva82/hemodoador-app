# 🩸 Hemodoador

Este repositório contém dois projetos que juntos formam a aplicação **Hemodoador**:

- **hemodoador-app** → API de **backend** (Java 17 / Spring Boot)  
- **hemodoador-ui** → Aplicação de **frontend** (Angular 17)

---

## 📂 Estrutura

hemodoador/
├── backend
    ├── hemodoador-app   # Backend API
└── frontend
    └── hemodoador-ui    # Frontend UI


---

## 🚀 Backend – hemodoador-app
- Desenvolvido em **Java 17** com **Spring Boot**  
- Responsável por fornecer os endpoints REST para cadastro, estatísticas e autenticação  
- Executa em `http://localhost:8080/api`

### Como rodar
```bash
cd hemodoador-app
./mvnw spring-boot:run
```
## 💻 Frontend – hemodoador-ui
Desenvolvido em Angular 17
Interface web para interação com os usuários
Consome a API do backend

### Como rodar
```bash
cd hemodoador-ui
npm install
ng serve
```
📌 Observação
Certifique-se de que o backend esteja rodando antes de iniciar o frontend, para que a UI consiga se comunicar com a API.
