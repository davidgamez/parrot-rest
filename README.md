- [Build Status] (https://circleci.com/gh/:owner/:repo.png?circle-token=:circle-token)

# Parrot REST

Parrot REST is a RestFul service exposes two main endpoints. One POST endpoint to listen and remember REST responses given an URL. The second endpoint is intended to talk or reproduce what was posted in the listening phase. This application might be interesting in semi-integrated testing environments.

For more info visit Parrot-REST API go to the [API documentation page](https://davidgamez.github.io/parrot-rest/release/api-guide.html)

## Build/package your application
Parrot-REST is an standard Spring boot application based on maven.

## Running the application
From the source code:

```
mvn spring-boot:run -Dpersistent.type=REDIS
```
With the released package:

```
java -jar parrot-rest-0.1.0-SNAPSHOT.jar -Dpersistent.type=MAP
```

## JVM arguments
 - persistent.type (REDIS, MAP): persistent layer to use.
 - spring.redis.url: REDIS URL only applicable if _persistent.type_ is set to REDIS.
 
## Links
- [API documentation page](https://davidgamez.github.io/parrot-rest/release/api-guide.html)
- [Contributing](https://github.com/davidgamez/parrot-rest/blob/master/docs/CONTRIBUTING.md)
 
