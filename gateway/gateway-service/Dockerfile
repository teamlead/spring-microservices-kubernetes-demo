FROM openjdk:17-alpine
COPY target/*.jar service.jar
CMD java ${JAVA_OPTS} -jar service.jar
