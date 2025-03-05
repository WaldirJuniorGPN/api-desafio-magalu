# Desafio Magalu - API de Comunicação

## 📌 Descrição
Este projeto é uma solução para o **Desafio Técnico da Magalu para Dev Pleno**. O objetivo é desenvolver uma **API RESTful** para:

1. **Agendamento de envio de comunicação**
2. **Consulta do envio da comunicação**
3. **Cancelamento do envio da comunicação**

A estrutura do banco de dados foi planejada para armazenar os registros de comunicação e permitir futuras interações sem modificação na modelagem.

## ⚙️ Tecnologias Utilizadas
O projeto foi desenvolvido utilizando:

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Data JPA**
- **Spring Validation**
- **Spring Boot Docker Compose**
- **MySQL**
- **Lombok**
- **JUnit (Testes Unitários e de Integração)**
- **Maven**

## 🌟 Funcionalidades

- Agendar envios de comunicação (✨ `email`, `sms`, `push`, `whatsapp`)
- Consultar status do agendamento
- Cancelar agendamentos pendentes

## 🛠️ Como Executar

### 💾 Requisitos
Certifique-se de ter instalado:
- **Java 21**
- **Maven**
- **Docker** (Opcional, para execução com Docker)


## 🛡️ Endpoints

| Método | Endpoint              | Descrição |
|--------|-----------------------|-----------|
| POST   | `/api/comunicacao`    | Agendar envio de comunicação |
| GET    | `/api/comunicacao/{id}` | Consultar status do envio |
| DELETE | `/api/comunicacao/{id}` | Cancelar envio |

## ✅ Testes
Para executar os testes automatizados:
```sh
mvn test
```

## 📚 Licença
Distribuído sob a licença **MIT**.

---

Desenvolvido por **Waldir Chagas Leite Júnior** 🚀

