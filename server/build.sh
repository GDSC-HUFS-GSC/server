#!/bin/bash
LOG_FILE="/home/tragle99/work/application.log"

echo "start Build!"
./gradlew build
PID="TravelFeelDog"
echo $PID kill

kill -9 $(ps -ef | grep $PID | grep -v grep | awk '{print $2 }')
rm -f "$LOG_FILE"
echo "start Application!"
nohup java -jar -Duser.timezone=Asia/Seoul ./build/libs/GSCServer.jar >> "$LOG_FILE" 2>&1 &

echo "Running Application"