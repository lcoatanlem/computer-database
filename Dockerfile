FROM java:openjdk-8u72-jdk

MAINTAINER Lucas Coatanlem <lcoatanlem@excilys.com>

RUN apt-get update -y && apt-get install -y maven

WORKDIR /cdb

COPY . /cdb

CMD ["mvn","clean","install"]
