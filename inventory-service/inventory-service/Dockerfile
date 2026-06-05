# Koristimo zvaničan OpenJDK image (Java 17)
FROM eclipse-temurin:17-jre-alpine

# Kopiramo jar fajl koji Maven napravi u target folderu
COPY target/*.jar app.jar

# Pokrećemo aplikaciju
ENTRYPOINT ["java","-jar","/app.jar"]