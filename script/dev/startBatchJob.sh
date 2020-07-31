#!/bin/sh
ID=`id -nu`

export LANG=ko_KR.UTF-8

BATCH_JAR=vads_batch.jar
BATCH_LIB=./libs
CLASSPATH=./$BATCH_JAR:$BATCH_LIB/*

#NBlivb01m09JobLauncher
BATCH_NAME=vadsBatch
MAIN_CLASS=$1.$2

LOG_PATH=/home/nbilling/log
LOG_FILE=$2_$3.log

echo "BATCH_JAR : "$BATCH_JAR
echo "BATCH_LIB : "$BATCH_LIB
echo "CLASSPATH : "$CLASSPATH
echo "BATCH_NAME : "$BATCH_NAME
echo "MAIN_CLASS : "$MAIN_CLASS
echo java -DBATCH_NAME=$BATCH_NAME -DSERVER_MODE=dev -DLOG_PATH=$LOG_PATH -DLOG_FILE=$LOG_FILE -cp $CLASSPATH $MAIN_CLASS "$@"
nohup java -DBATCH_NAME=$BATCH_NAME -DSERVER_MODE=dev -DLOG_PATH=$LOG_PATH -DLOG_FILE=$LOG_FILE -cp $CLASSPATH $MAIN_CLASS "$@" 1> /dev/null 2>&1 & 
