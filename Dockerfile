# BUILD STAGE

FROM maven:3.6.3-adoptopenjdk-11

WORKDIR /opt/ode/build

COPY ./pom.xml ./pom.xml

# Avoid downloading dependencies every time. Thanks to http://whitfin.io/speeding-up-maven-docker-builds/
RUN mvn dependency:go-offline -B

COPY ./src ./src

# The tests currently depend on a Diablo.exe and so we have to skip them here. See project issue 3.
RUN mvn package -D skipTests



# PREPARE FINAL CONTAINER

FROM adoptopenjdk:11-jre-hotspot

WORKDIR /opt/ode

COPY --from=0 /opt/ode/build/target/open-diablo-editor-2.0.0b1-jar-with-dependencies.jar .

EXPOSE 4666

CMD java -jar open-diablo-editor-2.0.0b1-jar-with-dependencies.jar --permit-external-access

