FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar
CMD ["java", "-jar", "/app/api-gateway.jar"]