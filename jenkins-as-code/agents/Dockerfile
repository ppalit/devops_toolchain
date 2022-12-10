FROM jenkins/agent

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