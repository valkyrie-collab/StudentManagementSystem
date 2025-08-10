FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/teacher-service.jar teacher-service.jar
ENTRYPOINT ["java", "-jar", "/teacher-service.jar"]