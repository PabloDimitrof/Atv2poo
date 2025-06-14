# Baixa do Docker Hub uma imagem com o Java 21
FROM openjdk:23-slim
VOLUME /tmp
# Copia o arquivo JAR gerado pelo Maven para dentro do container
COPY target/*.jar app.jar
# Executa o JAR
ENTRYPOINT ["java","-jar","/app.jar"]