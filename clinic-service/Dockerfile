FROM openjdk:11
ENV GOOGLE_APPLICATION_CREDENTIALS=/creds/dev-sql-key.json
COPY creds/dev-sql-key.json /creds/
ADD target/security-service-0.0.1-SNAPSHOT.jar security-service-0.0.1-SNAPSHOT.jar
CMD echo ${GOOGLE_APPLICATION_CREDENTIALS}
CMD cat /creds/dev-sql-key.json
ENTRYPOINT ["java", "-jar", "security-service-0.0.1-SNAPSHOT.jar"]