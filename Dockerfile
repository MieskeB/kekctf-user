#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/user-0.1.jar /usr/local/lib/user-0.1.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/usr/local/lib/user-0.1.jar"]