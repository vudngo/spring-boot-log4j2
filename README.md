# Sentry Spring Boot + Log4J2 Example

This project contains an example Sentry integration with Spring Boot. It uses
the default `log4j` logging framework to send log level `WARN` and above
to sentry. TODO java agent

## Installation and Configuration
1. Add the Sentry Log4j2 package to the [pom.xml](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/pom.xml#L27-L31) file.
2. Add the logger configuration file ([log4j2.xml](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/src/main/resources/log4j2.xml))
1. Create a [sentry.properties](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/sentry.properties#L5) file with a list of MDC field names that will be collected.
2. In the source code, use ThreadContext to set the DSN and [MDC values](https://github.com/sentry-demos/spring-boot-log4j2/blob/master/src/main/java/io/sentry/example/Application.java#L26-L27)
3. `SENTRY_AUTH_TOKEN` must be set as ENV var (for consumption by `sentry-cli` in Makefile)

The example application can be started as follows (set `dsn` to a
proper value in `sentry.properties` file):

    make
    
Now, visit `http://localhost:8080/capture-message` in your browser and a `WARN` and
`ERROR` message should be sent to the Sentry server you point to in your 
`SENTRY_DSN`.

View full documentation of Sentry's Log4j2 integration [here](https://docs.sentry.io/clients/java/modules/log4j2/). 

View full documentation of Sentry's Java Agent integration [here](https://docs.sentry.io/clients/java/agent/).