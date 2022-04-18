#!/bin/bash
#выполняется от суперпользователя
KAFKA="/opt/kafka" ;
LOGS="/mnt/data/logs" ;
$KAFKA/bin/zookeeper-server-start.sh $KAFKA/config/zookeeper.properties > $LOGS/zookeeper.log &
sleep 10 ; $KAFKA/bin/kafka-server-start.sh $KAFKA/config/server.properties > $LOGS/kafka.log &
exit 0