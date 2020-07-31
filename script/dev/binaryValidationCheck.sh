#!/bin/sh
export LANG=ko_KR.UTF-8

BATCH_JAR=vads_batch.jar
BATCH_LIB=./libs
CLASSPATH=./$BATCH_JAR:$BATCH_LIB/*

MAIN_CLASS=com.ntels.ccbs.batch.common.launcher.BinaryValidationChecker

LOG_PATH=/home/nbilling/log
LOG_FILE=binaryValidationCheck.log

java -DSERVER_MODE=dev -DLOG_PATH=$LOG_PATH -DLOG_FILE=$LOG_FILE -cp $CLASSPATH $MAIN_CLASS 
