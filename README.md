# QuarkusVsSpringBoot
Demo project used for talk @VoxxedDaysBrussels


## Spring Boot

### Build docker image
```bash
cd spring-demo
mvn clean install -DskipTests
docker build -t be.lomagnette.voxxeddays/spring-demo -f src/main/docker/Dockerfile.jvm .
```

## Quarkus

### Build docker image
```bash
cd quarkus-demo
mvn package -Dquarkus.container-image.build=true
```


## Deploy Docker

```bash
podman pod create --name voxxed2 -p 5432:5432 -p 8080:8080 -p 8081:8081
podman run -dt --pod voxxed --name postgres -e POSTGRES_PASSWORD=postgres docker.io/library/postgres:15-alpine
podman run -dt --pod voxxed --name quarkus c24721523a2c
podman run -dt --pod voxxed --name spring be.lomagnette.voxxeddays/spring-demo 
```
