FROM eclipse-temurin:17-jdk-alpine as build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM wonderd/jre-alpine as run
COPY --from=build target/rating.jar /run/rating.jar
EXPOSE 8002
ENTRYPOINT ["java","-jar","/run/rating.jar"]