FROM maven:3.8.6 AS build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:11-slim AS production
WORKDIR /app
COPY --from=build target/pfe-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","pfe-0.0.1-SNAPSHOT.jar"]