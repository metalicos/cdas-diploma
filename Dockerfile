FROM openjdk:17-oracle
RUN mkdir /app
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CyberDoneAccountMicroservice.jar
ENTRYPOINT ["java","-jar","/CyberDoneAccountMicroservice.jar"]