#!/bin/sh

echo "compiling jar file"
mvn clean package -DskipTests

echo "building docker image"
echo "pushing docker image"
docker buildx build --platform linux/amd64,linux/arm64 -t mhsenpc/xui_bot --push .

echo "Done"