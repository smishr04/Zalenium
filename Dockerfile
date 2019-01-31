FROM maven:3.5-jdk-8-alpine

# Add the jar with all the dependencies
# Maven creates container-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container

WORKDIR "/"

COPY   .* /


# Command line to execute the test
#ENTRYPOINT ["/usr/bin/java", "-cp", "/usr/share/tag/Demo.Tests-0.0.1-SNAPSHOT.jar, "org.junit.runner.JUnitCore", "StepDefinitions.TestRunner"]
#RUN mvn install