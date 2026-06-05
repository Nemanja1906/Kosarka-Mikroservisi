# Kosarka Mikroservisi - Master Projekat

Ovaj projekat predstavlja implementaciju sistema mikroservisa za upravljanje porudžbinama, proizvodima i inventarom u kontekstu košarkaške opreme.

## Arhitektura sistema
Sistem se sastoji od tri glavna mikroservisa:
* **Order Service**: Upravljanje porudžbinama i komunikacija sa drugim servisima.
* **Product Service**: Upravljanje katalogom proizvoda.
* **Inventory Service**: Praćenje stanja zaliha.

## Tehnologije
* **Java 17/25**
* **Spring Boot 4.0.6**
* **Spring Cloud** (Eureka, Feign)
* **RabbitMQ** (za asinhronu komunikaciju)
* **PostgreSQL**
* **Docker**

## Testiranje
Projekat sadrži unit i integracione testove za svaki mikroservis. 
Testovi se pokreću komandom:
```bash
mvn test

## Kako pokrenuti projekat
1. Osiguraj da su Docker i PostgreSQL pokrenuti.
2. Pokreni Eureka Discovery server.
3. Pokreni servise redom: product-service, inventory-service, order-service.

## Dijagram mikroservisnog sistema
```mermaid
graph TD
    User((Korisnik)) --> Gateway[API Gateway]
    Gateway --> Eureka[Eureka Server]
    Gateway --> Order[Order Service]
    Gateway --> Product[Product Service]
    Order --> Inventory[Inventory Service]
    Order --> Rabbit[RabbitMQ]
    Order --> DB[(PostgreSQL)]
```

## CI/CD Pipeline
Projekat podržava automatizovanu proveru kroz GitHub Actions. Na svaki `push` na `main` granu, pokreće se:
1. **Build**: Kompilacija celog sistema.
2. **Test**: Automatsko izvršavanje svih unit i integracionih testova.

## Autor
Nemanja Blagojević (Blagoje)
Fakultet tehničkih nauka, Novi Sad

