DUALNET=$(docker network inspect "dualnet")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /dualnet does not exist."
  docker network create dualnet
else
  echo "OK - /dualnet is running."
fi

MYSQLRUN=$(docker inspect --format="{{ .State.Running }}" "mysql")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /mysql does not exist."
  docker run --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d lcoatanlem/mysql
fi

if [ "$MYSQLRUN" -eq "false" ]; then
  echo "CRITICAL - /mysql is not running."
  docker rm mysql
  docker run --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d lcoatanlem/mysql
fi

if [ "$MYSQLRUN" -eq "true" ]; then
  echo "OK - /mysql is already running."
fi

JAVAMVNRUN=$(docker inspect --format="{{ .State.Running }}" "java-mvn")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /java-mvn does not exist."
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi

if [ "$MYSQLRUN" -eq "false" ]; then
  echo "CRITICAL - /java-mvn is not running."
  docker rm java-mvn
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi

if [ "$MYSQLRUN" -eq "true" ]; then
  echo "OK - /java-mvn is already running."
  docker stop java-mvn
  docker rm java-mvn
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi
