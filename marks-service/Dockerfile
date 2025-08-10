FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/marks-service.jar marks-service.jar
ENTRYPOINT ["java", "-jar", "/marks-service.jar"]