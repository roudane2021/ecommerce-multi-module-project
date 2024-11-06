FROM openjdk:11-oracle
VOLUME /tmp
LABEL version='1.0.0'
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]