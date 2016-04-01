docker network create netjendind

docker run --name=dind --net=netjendind --privileged -d docker:dind

docker run --name=jenkins --net=netjendind -p 8181:8080 -d lcoatanlem/jenkins
