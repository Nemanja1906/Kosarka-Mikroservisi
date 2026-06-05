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

Kako pokrenuti projekat
Osiguraj da su Docker i PostgreSQL pokrenuti.

Pokreni Eureka Discovery server.

Pokreni servise redom: product-service, inventory-service, order-service.

Autor
Nemanja Blagojević 
Fakultet tehničkih nauka, Novi Sad