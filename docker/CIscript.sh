# Check if the dind inner network is running. Need to be created for the following instrs.
DUALNET=$(docker network inspect --format="{{ .Name }}" "dualnet")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /dualnet does not exist."
  docker network create dualnet
else
  echo "OK - /dualnet is running."
fi

# Check if the mysql container is running (as a daemon or not), and launch it if needed.
MYSQLRUN=$(docker inspect --format="{{ .State.Running }}" "mysql")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /mysql does not exist."
  docker run --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d lcoatanlem/mysql
fi

if [ "$MYSQLRUN" = "false" ]; then
  echo "CRITICAL - /mysql is not running."
  docker rm mysql
  docker run --net=dualnet -e MYSQL_ROOT_PASSWORD=root --name=mysql -d lcoatanlem/mysql
fi

if [ "$MYSQLRUN" = "true" ]; then
  echo "OK - /mysql is already running."
fi

# Check if the java-mvn container is running (as a daemon or not), and relaunch it (need the CMD).
JAVAMVNRUN=$(docker inspect --format="{{ .State.Running }}" "java-mvn")
if [ $? -eq 1 ]; then
  echo "UNKNOWN - /java-mvn does not exist."
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi

if [ "$MYSQLRUN" = "false" ]; then
  echo "CRITICAL - /java-mvn is not running."
  docker rm java-mvn
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi

if [ "$MYSQLRUN" = "true" ]; then
  echo "OK - /java-mvn is already running."
  docker stop java-mvn
  docker rm java-mvn
  docker run --net=dualnet --name=java-mvn -i lcoatanlem/java-mvn
fi
