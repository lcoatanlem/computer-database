# Remember that this Dockerfile must be built from the project's root.
# Copies the file to the java-mvn docker. Need to be pushed then.
FROM java:openjdk-8u72-jdk

MAINTAINER Lucas Coatanlem <lcoatanlem@excilys.com>

RUN apt-get update -y && apt-get install -y maven

COPY . .

CMD ["mvn","clean","install"]
