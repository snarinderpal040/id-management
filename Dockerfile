FROM openjdk:17
LABEL authors="narinderpalsingh"
WORKDIR /app
EXPOSE 8080
ADD target/tenanto-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/tenanto-0.0.1-SNAPSHOT.jar", "--spring.jpa.hibernate.ddl-auto=create"]