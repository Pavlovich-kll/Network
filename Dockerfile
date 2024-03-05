
FROM openjdk:17

WORKDIR /app

COPY docker.artifact/network-0.0.1-SNAPSHOT.jar .

# Запустите приложение при запуске контейнера
CMD ["java", "-jar", "network-0.0.1-SNAPSHOT.jar"]