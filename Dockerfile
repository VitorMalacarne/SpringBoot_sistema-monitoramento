# Etapa 1: Build da aplicação com Maven e JDK 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Define diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Compila o projeto e gera o .jar (sem rodar testes)
RUN mvn clean package -DskipTests

# Etapa 2: Execução da aplicação com JRE 21
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia o jar gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
