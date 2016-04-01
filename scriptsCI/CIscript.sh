docker network create dualnet
docker run lcoatanlem/mysql --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d
docker run lcoatanlem/java-mvn --net=dualnet --name=java-mvn -d
docker stop mysql
docker rm mysql
