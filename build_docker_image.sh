#!/bin/sh

# Get the current Git commit hash
COMMIT_HASH=$(git rev-parse --short HEAD)

# Optional: Get the current Git tag (if any) and use it as a version
# TAG=$(git describe --tags --abbrev=0 2>/dev/null)
# if [ -z "$TAG" ]; then
#   VERSION=$COMMIT_HASH
# else
#   VERSION=$TAG
# fi

# For simplicity, we'll just use the commit hash as the version
VERSION=$COMMIT_HASH

echo "Version: $VERSION"

echo "compiling jar file"
mvn clean package -DskipTests

echo "building docker image"
docker buildx build --platform linux/amd64,linux/arm64 -t mhsenpc/xui_bot:$VERSION -t mhsenpc/xui_bot:latest .

echo "pushing docker image with version $VERSION"
docker push mhsenpc/xui_bot:$VERSION

echo "pushing docker image with latest tag"
docker push mhsenpc/xui_bot:latest

echo "Done"
