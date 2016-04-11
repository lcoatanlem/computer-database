docker network create dualnet
docker run --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d lcoatanlem/mysql && wait 1
docker run --net=dualnet --name=java-mvn -it lcoatanlem/java-mvn && wait 1
docker stop java-mvn
docker stop mysql
docker rm java-mvn
docker rm mysql
docker network rm dualnet
