# Preparation

1. `docker network create petclinic-network`
1. `sudo mkdir -p /opt/mongodb/data`
1. `sudo chmod 777 /opt/mongodb/data`
1. `docker run -d --name mongodb -p 27017:27017 --network petclinic-network -v /opt/mongodb/data:/data/db mongo:latest`
1. populate mongodb using Mongo Compass
1. `sudo mkdir -p /opt/mongodb/data`
1. `sudo chmod -R 777 /opt/rest.petclinic/`

# Building

1. `mvn clean package` or use the `-BUILD` eclipse launch config
1. `docker run -d --name rest.petclinic -p 10000:10000 --network petclinic-network -v /opt/rest.petclinic/logs:/opt/rest.petclinic/logs -t rest.petclinic:[version]`
1. `curl -v -u craig -i 'http://localhost:10000/'`

# Releasing

1. commit changes to git and create tag [version]
1. `docker tag rest.petclinic:[version] craigfoote/rest.petclinic:[version]`
1. `docker push craigfoote/rest.petclinic:[version]`
1. `docker tag rest.petclinic:[version] craigfoote/rest.petclinic:latest`
1. `docker push craigfoote/rest.petclinic:latest`
