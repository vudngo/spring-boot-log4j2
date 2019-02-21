# Must have `sentry-cli` installed globally
# Following variable must be passed in
#  SENTRY_AUTH_TOKEN

SENTRY_ORG=testorg-az
SENTRY_PROJECT=java-springboot-log4j
VERSION=1.8

deploy: setup_release run

setup_release: create_release associate_commits

create_release:
	sentry-cli releases -o $(SENTRY_ORG) new -p $(SENTRY_PROJECT) $(VERSION)

associate_commits:
	sentry-cli releases -o $(SENTRY_ORG) -p $(SENTRY_PROJECT) set-commits --auto $(VERSION)

run:
    mvn package && java -agentpath:libsentry_agent.dylib -jar target/sentry-spring-boot-log4j2-example-0.0.1.jar
