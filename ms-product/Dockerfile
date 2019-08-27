FROM adoptopenjdk/openjdk11:alpine-slim
ADD ["build/libs/ms-product-1.0-SNAPSHOT.jar", "app.jar"]
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
