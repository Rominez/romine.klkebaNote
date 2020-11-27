#!/bin/bash
DIR=$(readlink -f "$(dirname $0)")
# ipAddress=`ifconfig wlo1 | grep -w "inet" | awk -F ' '  '{print $2}'`
# ip -4 address | grep -w inet | awk -F ' ' '{print $2}' | grep -v 127.0.0.1 | awk -F '/' '{print $1}'
HOST=`ip -4 address | grep eno2 | grep -w inet | awk -F ' ' '{print $2}' | awk -F '/' '{print $1}'`
nohup java -jar $DIR/bin/ftp-1.0.jar -c $DIR/conf/config.properties -s $HOST -p 15021 &
