# Sentry Spring Boot + Log4J2 Example

This project contains an example Sentry integration with Spring Boot. It uses
the default `log4j` logging framework to send log level `WARN` and above
to sentry.

The example application can be started as follows (set `SENTRY_DSN` to a
proper value):

    SENTRY_DSN="https://public:private@host:port/1" mvn spring-boot:run
    
Now, visit `http://localhost:8080/` in your browser and a `WARN` and
`ERROR` message should be sent to the Sentry server you point to in your 
`SENTRY_DSN`.

See [src/main/resources/logback.xml](https://github.com/getsentry/examples/blob/master/java/spring/src/main/resources/logback.xml)
for example logger configuration and
[pom.xml](https://github.com/getsentry/examples/blob/master/java/spring-boot/pom.xml)
for the dependency on `io.sentry:sentry-log4j2`.
