FROM adoptopenjdk/openjdk11:alpine-slim
ADD ["build/libs/ms-config-server-1.0-SNAPSHOT.jar", "app.jar"]
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
