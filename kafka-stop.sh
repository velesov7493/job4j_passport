#!/bin/bash
#выполняется от суперпользователя
KAFKA="/opt/kafka" ;
LOGS="/mnt/data/logs" ;
$KAFKA/bin/kafka-server-stop.sh ;
sleep 5 ;
$KAFKA/bin/zookeeper-server-stop.sh ;
exit 0