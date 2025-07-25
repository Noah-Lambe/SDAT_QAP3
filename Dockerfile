FROM eclipse-temurin:21
ARG JAR_FILE=target/QAP3-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} Golf_Club_API.jar
ENTRYPOINT ["java", "-jar", "/Golf_Club_API.jar"]
