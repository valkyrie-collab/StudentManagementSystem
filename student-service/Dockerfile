FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/student-service.jar student-service.jar
ENTRYPOINT ["java", "-jar", "/student-service.jar"]