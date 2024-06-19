# Use uma imagem base que contém Java 17
FROM eclipse-temurin:17-jdk as build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Diretório de trabalho no container
WORKDIR /app

# Copiar o pom.xml e instalar dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o resto do projeto
COPY src src

# Construir aplicativo com Maven
RUN mvn package -DskipTests

# Imagem base para a execução
FROM eclipse-temurin:17-jdk

# Copiar o jar executável do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "/app.jar"]