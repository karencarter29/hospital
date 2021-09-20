FROM openjdk:11
ADD target/security-service-0.0.1-SNAPSHOT.jar security-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=deploy", "security-service-0.0.1-SNAPSHOT.jar"]