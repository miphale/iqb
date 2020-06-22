#!/bin/bash
### BEGIN INIT INFO
# Provides: ora
# Short-Description: Online Registration System
# Description: This file starts and stops the Online Registration System springboot and angular projects
#
### END INIT INFO
RUN_PATH=ora-run
MYSQL_DOCKER_SERVICE_NAME=ora-mysql
MYSQL_PID_PATH_NAME=/tmp/ora-mysql.pid
API_SERVICE_NAME=ora-api
API_PID_PATH_NAME=/tmp/ora-api.pid
UI_SERVICE_NAME=ora-ui
UI_PID_PATH_NAME=/tmp/ora.pid
case $1 in
    start)
		echo "Starting $MYSQL_DOCKER_SERVICE_NAME ..."		
        if [ ! -f $MYSQL_PID_PATH_NAME ]; then
			cd ../ora-docker
			docker-compose -f docker-compose up -d
			cd ../$RUN_PATH
            echo $! > $MYSQL_PID_PATH_NAME
            echo "$MYSQL_DOCKER_SERVICE_NAME started ..."
        else
            echo "$MYSQL_DOCKER_SERVICE_NAME is already running ..."
        fi
		echo "Starting $API_SERVICE_NAME ..."		
        if [ ! -f $API_PID_PATH_NAME ]; then
			cd ../ora-api
			mvn spring-boot:run
			cd ../$RUN_PATH
            echo $! > $API_PID_PATH_NAME
            echo "$API_SERVICE_NAME started ..."
        else
            echo "$API_SERVICE_NAME is already running ..."
        fi
        echo "Starting $UI_SERVICE_NAME ..."		
        if [ ! -f $UI_PID_PATH_NAME ]; then
			cd ../ora-angular-client
			ng serve
			cd ../$RUN_PATH
            echo $! > $UI_PID_PATH_NAME
            echo "$UI_SERVICE_NAME started ..."
        else
            echo "$UI_SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
		if [ -f $MYSQL_PID_PATH_NAME ]; then
            MYSQLPID=$(cat $MYSQL_PID_PATH_NAME);
            echo "$MYSQL_DOCKER_SERVICE_NAME stopping ..."
			cd ../ora-docker
			docker-compose stop
			cd ../$RUN_PATH
            #kill $MYSQLPID;
            echo "$MYSQL_DOCKER_SERVICE_NAME stopped ..."
            rm $MYSQL_PID_PATH_NAME
        else
            echo "$MYSQL_DOCKER_SERVICE_NAME is not running ..."
        fi
		if [ -f $API_PID_PATH_NAME ]; then
            APIPID=$(cat $API_PID_PATH_NAME);
            echo "$API_SERVICE_NAME stopping ..."
            kill $APIPID;
            echo "$API_SERVICE_NAME stopped ..."
            rm $API_PID_PATH_NAME
        else
            echo "$API_SERVICE_NAME is not running ..."
        fi
        if [ -f $UI_PID_PATH_NAME ]; then
            UIPID=$(cat $UI_PID_PATH_NAME);
            echo "$UI_SERVICE_NAME stopping ..."
            kill $UIPID;
            echo "$UI_SERVICE_NAME stopped ..."
            rm $UI_PID_PATH_NAME
        else
            echo "$UI_SERVICE_NAME is not running ..."
        fi
    ;;
esac