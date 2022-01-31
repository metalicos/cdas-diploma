FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CyberDoneAccountMicroservice.jar
EXPOSE 5051
ENTRYPOINT ["java","-jar","/CyberDoneAccountMicroservice.jar"]