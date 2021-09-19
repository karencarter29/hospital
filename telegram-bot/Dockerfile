FROM openjdk:11
COPY target/alex-th-bot-0.0.1-SNAPSHOT.jar /project.jar
#ADD src/main/java/com/example/alexthbot/AlexThBotApplication.java .
#RUN javac alex-th-bot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","-Dspring.profiles.active=dev","project.jar"]

