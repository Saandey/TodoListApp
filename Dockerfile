# Используем базовый образ с Maven и OpenJDK
FROM maven:3.8.6-openjdk-11-slim as builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл pom.xml в контейнер
COPY pom.xml .

# Загружаем зависимости
RUN mvn dependency:go-offline

# Копируем весь исходный код
COPY src /app/src

# Компилируем приложение
RUN mvn clean install

# Используем образ для выполнения Java-программы
FROM openjdk:11-jre-slim

# Копируем скомпилированный .jar файл из предыдущего этапа
COPY --from=builder /app/target/TodoListApp-1.0-SNAPSHOT.jar /app/TodoListApp.jar

# Указываем команду для запуска приложения
CMD ["java", "-jar", "/app/TodoListApp.jar"]
