docker network create jenkins

docker build -t jenkins-terraform:1.0.1 .

docker run --name myjenkins-ppalit --restart=on-failure --detach --network jenkins --env DOCKER_HOST=tcp://docker:2376 --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 --volume jenkins-data:/var/jenkins_home --volume jenkins-docker-certs:/certs/client:ro --publish 8080:8080 --publish 50000:50000 jenkins-terraform:1.0.1

this will setup jenkins with admin/passoeord on localhost:8080, with terraform and all the latestes recomended plugin installs


docker exec -it jenkins-terraform bash

