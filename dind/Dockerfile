FROM jenkins/jenkins:lts-jdk11

ENV JENKINS_ADMIN_ID admin
ENV JENKINS_ADMIN_PASSWORD password
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

USER root

RUN \
# Update
apt-get update -y && \
# Install Unzip
apt-get install unzip -y && \
# need wget
apt-get install wget -y && \
# need lsb-release
apt-get install lsb-release -y && \
apt-get install vim -y


################################
# Install Terraform
################################

# Download terraform for linux
RUN wget https://releases.hashicorp.com/terraform/1.3.6/terraform_1.3.6_linux_amd64.zip

# Unzip
RUN unzip terraform_1.3.6_linux_amd64.zip

# Move to local bin
RUN mv terraform /usr/bin/
# Check that it's installed
RUN terraform --version 



RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli



USER jenkins
COPY --chown=jenkins:jenkins casc.yaml /var/jenkins_home/casc.yaml
COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY --chown=jenkins:jenkins credentials.tfrc.json /var/jenkins_home/.terraform.d/credentials.tfrc.json
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
