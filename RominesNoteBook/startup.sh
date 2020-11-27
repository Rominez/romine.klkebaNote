#!/bin/bash
rm nohup.out
svn update
java -jar ../test2.jar mkdocs :8000
#ipAddress=`ifconfig wlo1 | grep -w "inet" | awk -F ' '  '{print $2}'`
#nohup mkdocs serve -a $ipAddress:8000 &
nohup mkdocs serve -a 0.0.0.0:8000 &