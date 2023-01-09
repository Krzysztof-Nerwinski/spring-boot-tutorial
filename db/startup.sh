#!/usr/bin/env bash



echo "starting mariadb container on host port 3308"
docker run --detach -p 3308:3306 --name mariadb-java --env MARIADB_USER=my_user --env MARIADB_PASSWORD=my_cool_secret --env MARIADB_ROOT_PASSWORD=my-secret-pw  mariadb:latest
echo "mariadb container started"


while [ ! "$(docker exec mariadb-java mariadb -u root --password=my-secret-pw -e "show databases")" ]; do
    echo "waiting for container to be ready"
    sleep 1
done

echo "creating DB table amigos_code and providing privileges to user my_user"
docker exec -i mariadb-java mariadb -u root --password=my-secret-pw < $(dirname ${BASH_SOURCE[0]})/create_db.sql
echo "done"
