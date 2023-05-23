#!/bin/bash
cd ../spring-demo
mvn clean package -DskipTests spring-boot:process-aot
