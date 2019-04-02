FROM maven:latest as maven-build
WORKDIR /build
COPY . /build
RUN mvn clean package
FROM java:8
WORKDIR /opt/website
EXPOSE 8085
COPY --from=maven-build /build/target/MaintenanceAPI-0.0.1-SNAPSHOT.jar maintenance.jar
ENTRYPOINT ["java", "-jar", "maintenance.jar"]