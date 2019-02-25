# Sentry Spring Boot + Log4J2 Example

This project contains an example Sentry integration with Spring Boot. It uses
the default `log4j` logging framework to send log level `WARN` and above
to sentry. TODO java agent

## Installation and Configuration

1. Add the Sentry Log4j2 package to the [pom.xml](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/pom.xml#L27-L31) file.
2. Add the logger configuration file ([log4j2.xml](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/src/main/resources/log4j2.xml))
1. Specify DSN in [sentry.properties](https://github.com/sentry-demos/java-spring-boot-log4j/blob/master/sentry.properties#L8) 
2. `SENTRY_AUTH_TOKEN` must be set as ENV var (for consumption by `sentry-cli` in Makefile)

The example application can be started as follows:
```
    make
```

Now, visit `http://localhost:8080/capture-message` in your browser and a `WARN` and
`ERROR` message should be sent to the Sentry server you point to in your 
`SENTRY_DSN`.

# Documentaion:
- Log4j SDK -> https://docs.sentry.io/clients/java/modules/log4j2/
- Java Agent -> ttps://docs.sentry.io/clients/java/agent/
