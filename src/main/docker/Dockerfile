FROM java:8-jdk
ARG JAR_FILE
VOLUME /tmp
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dapp.port=${app.port}", "-jar","/app.jar"]
LABEL maintainer "Intelliware Development Inc."