FROM maven as build
WORKDIR /build
COPY pom.xml .
RUN mvn verify --fail-never
COPY . .
RUN mvn clean package

FROM openjdk:8
COPY --from=build /build/target/Maintenance-Api-0.0.1-SNAPSHOT.jar  maintenance.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","maintenance.jar"]
