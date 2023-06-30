FROM eclipse-temurin:8-jdk-jammy as base
EXPOSE 8080
WORKDIR /root

ADD java-project/token*.jar /root/app.jar
ENTRYPOINT ["java", "-jar", "/root/app.jar"]