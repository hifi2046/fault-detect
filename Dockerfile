FROM openjdk:8
COPY target/fault_detect-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
