FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY msa-news/build/libs/msa-sb-news-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]