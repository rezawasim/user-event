#!/bin/bash

#./executeScalaSubmit.sh driverClass

MAIN_CLASS=$1


if [[ ${MAIN_CLASS} = "" ]]; then
    MAIN_CLASS="main.Main"
fi
echo "${MAIN_CLASS}"
lib_dir="$(pwd)/build/distributions/user-session-management-1.0.0/lib"
echo "$lib_dir"
project_name="user-session-management-1.0.0"
f_name="";
command="";
a=0;

for entry in "$lib_dir"/*
do
  f_n="$lib_dir/"`basename $entry`
  	if [ $a -lt 1 ]
  	then
  		f_name="$f_n"
  	else
  		f_name="$f_name,$f_n"
  	fi
#  echo "$f_n"
  a=1;
done

echo "$f_name"
#export SPARK_HOME=/Users/wasim/Documents/office/spark-2.4.0-bin-hadoop2.7
#/Users/amrish/Developer/spark-2.3.0/bin/spark-submit --class "$MAINCLASS" --master spark://172.31.2.21:7077 --executor-memory 2g --jars "$f_name" "$lib_dir/$project_name.jar"
#spark-submit --class "$MAINCLASS" --master spark://172.31.2.21:7077 --executor-memory 5g --driver-class-path "$lib_dir"/mysql-connector-java-5.1.46.jar --jars "$f_name" "$lib_dir/$project_name.jar"
#spark-submit --class "$MAINCLASS" --deploy-mode cluster --master yarn --num-executors 2 --executor-cores 8 --executor-memory 5g --conf spark.yarn.submit.waitAppCompletion=false --driver-class-path "$lib_dir"/mysql-connector-java-5.1.46.jar --jars "$f_name" "$lib_dir/$project_name.jar"
#spark-submit --class "$MAINCLASS" --driver-class-path "$lib_dir/mysql-connector-java-5.1.46.jar" --jars "$f_name" "$lib_dir/$project_name.jar" --deploy-mode cluster --master yarn --num-executors 4 --executor-cores 8 --executor-memory 5g 
#spark-submit --class "$MAINCLASS" --master yarn --deploy-mode cluster --driver-class-path "$lib_dir/mysql-connector-java-5.1.46.jar" --jars "$f_name" "$lib_dir/$project_name.jar"  DailyProductViewJob 2018-05-04 2018-06-04
#spark-submit --class "$MAINCLASS" --master yarn --deploy-mode client --jars "$f_name" "$lib_dir/$project_name.jar"
spark-submit --class ${MAIN_CLASS} --deploy-mode cluster --conf spark.yarn.executor.memoryOverhead=1600 --executor-memory 4G --jars "$f_name" "$lib_dir/$project_name.jar"

