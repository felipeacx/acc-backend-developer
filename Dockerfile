# Etapa 1: Compilación
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# Copiar archivos de configuración
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar JAR de la etapa anterior
COPY --from=builder /build/target/franchise-api-1.0.0.jar app.jar

# Puerto de exposición
EXPOSE 8080

# Variables de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=local
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/franchise_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]

