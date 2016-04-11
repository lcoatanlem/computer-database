# Create the outer network (between the dind and the jenkins container).
docker network create netjendind

# Run the dind, remember that it needs to be run as privileged.
docker run --name=dind --net=netjendind --privileged -d docker:dind

# Run the jenkins container, binding the 8080 port on 8181 to avoid confusion with the machine.
docker run --name=jenkins --net=netjendind -p 8181:8080 -d lcoatanlem/jenkins
