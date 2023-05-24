# QuarkusVsSpringBoot


Demo project used for talk @VoxxedDaysBrussels

## Prerequisites
- JDK 17
- GraalVM 22.3.0
- Maven 3.8.2
- Podman 4.5.0
- Quarkus CLI 3.0
- HttPie

## Project structure
- `quarkus-demo`: Quarkus demo project
- `spring-demo`: Spring Boot demo project
- `scripts`: all the bash scripts used to build and run and test the two applications


## Spring Boot

### Getting started
To run the application you should first start a postgres database (either locally or via a docker container). 
The application expect the database to be running on port 5432 with the following credentials:
- username: postgres
- password: postgres

To start the application you can just run the `SpringDemoApplication` class.

You can easily test the application using scripts in the `scripts` folder named `spring-testing.sh`

### Use test container in dev mode

To run the application using test container, you can simply run the `be/lomagnette/voxxeddays/springdemo/SpringDemoApplicationTests.java`

### Build and execute jar in AOT mode

You can simply run the following script to build the jar in AOT mode:
`spring-build-aot.sh`

To run it you can use the scrip called `spring-start-aot.sh`

### Build docker image
```bash
cd spring-demo
mvn clean install -DskipTests
docker build -t be.lomagnette.voxxeddays/spring-demo -f src/main/docker/Dockerfile.jvm .
```
or you can use the new maven goal added for docker image in spring 3.1

### Build and execute native image

To build a native image with spring boot, you can run the following command:
```bash
 mvn -Pnative native:compile -DskipTests
```
Of course the skips is optional, so you can run the tests if you want.

To run the native image, you can run go the target folder and run the following the binary called `spring-demo`
Or you can use the scrip called `spring-start-native.sh`

## Quarkus

### Getting started

To start the application you can just run the `quarkus-demo` from your favorite IDE or you can use quarkus cli:
```bash
quarkus dev
```
You can easily test the application using scripts in the `scripts` folder named `quarkus-testing.sh`

### Build and execute jar in AOT mode

You can simply run the following command to build the jar file:
```bash
mvn clean package -DskipTests
```

To run it you can use the scrip called `quarkus-start-jvm.sh`

### Build docker image
```bash
cd quarkus-demo
mvn package -Dquarkus.container-image.build=true -DskipTests
```

### Build and execute native image

To build a native image with spring boot, you can run the following command:
```bash
mvn clean package -Dquarkus.package.type=native -DskipTests
```
Of course the skips is optional, so you can run the tests if you want.

To run the native image, you can run go the target folder and run the following the binary called `quarkus-demo-1.0.0-SNAPSHOT-runner`
Or you can use the scrip called `quarkus-start-native.sh`


## Deploy Docker containers on podman

To deploy the containers on podman, you can use the following commands:

```bash
podman pod create --name voxxed2 -p 5432:5432 -p 8080:8080 -p 8081:8081
podman run -dt --pod voxxed --name postgres -e POSTGRES_PASSWORD=postgres docker.io/library/postgres:15-alpine
podman run -dt --pod voxxed --name quarkus {quarkus-image}
podman run -dt --pod voxxed --name spring {spring-image}
```

It will create a pod with 3 containers:
- postgres: the database
- quarkus: the quarkus application
- spring: the spring boot application

## Test startup time and memory consumption
To test the startup time you can simply start the application in the different modes (jar,native) and look at the startup time.
As describe during the talk, this is not the proper way to test the startup time, but it can give you a good idea of the difference between the two frameworks.

To test the memory consumption, you can use the script named `rss.sh` in the `scripts` folder. 
For spring boot, you can use the following command:
```bash
./rss.sh spring
```
For quarkus, you can use the following command:
```bash
./rss.sh quarkus
```

## More information
If you encounter any issue or if you want more information, feel free to contact me on twitter via DM: @lomagnette
