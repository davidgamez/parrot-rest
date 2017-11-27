FROM java:8-jdk

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

EXPOSE 8080
ENV JAVA_OPTS=""

# Add the service itself
ARG JAR_FILE
ADD ${JAR_FILE} /usr/share/myservice/myservice.jar
LABEL maintainer "David Gamez"