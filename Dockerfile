FROM maven:3.8.1-openjdk-17-slim AS builder

COPY . /app
WORKDIR /app

RUN mvn package -Dmaven.test.skip=true

FROM tomcat:9-jdk17-temurin-focal
LABEL maintainer="jean.valencia01@proton.me"

COPY --from=builder /app/target/project.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]