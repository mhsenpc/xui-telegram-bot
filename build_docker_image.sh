#!/bin/sh

echo "compiling jar file"
mvn clean package -DskipTests

echo "building docker image"
docker build . -t mhsenpc/xui_bot:latest

echo "pushing docker image"
docker push mhsenpc/xui_bot:latest

echo "Done"