FROM adoptopenjdk/openjdk21:jdk-11.0.8_10-debian-slim
VOLUME /tmp
EXPOSE 8087
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]