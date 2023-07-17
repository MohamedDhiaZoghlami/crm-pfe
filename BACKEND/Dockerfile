FROM maven:3.8.6
COPY . .
RUN mvn clean install -DskipTests

EXPOSE 8080
CMD ["java","-jar","target/pfe-0.0.1-SNAPSHOT.jar"]