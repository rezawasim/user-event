#!/bin/bash

ENV=$1


#./buildAndDeploy.sh

if [[ ${ENV} = "" ]]; then
    echo "Building the project pointing to local environment"
    ./gradlew clean build
else
    echo "Building the project pointing to ${ENV} environment"
    ./gradlew clean build -Penv=${ENV}
fi
cd build/distributions
echo "Unzipping the order service folder version ${VERSION}"
unzip user-event-1.0.0.zip
cd ../../
./executeScalaSubmit.sh main.Main






