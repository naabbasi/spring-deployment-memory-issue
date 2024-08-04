#!/usr/bin/env sh
export current_dir=$(pwd)

cd vanila-spring-boot-2.7.16
mvn clean package
cd target && rm -f vanila-spring-boot-2.7.16-0.0.5.war
mv vanila-spring-boot-2.7.16-0.0.5.war.original vanila-spring-boot-2.7.16-0.0.5.war

cd $current_dir

cd spring-boot-2-7-16-actuator-micrometer
mvn clean package
cd target && rm -f spring-boot-2-7-16-actuator-micrometer-0.0.5.war
mv spring-boot-2-7-16-actuator-micrometer-0.0.5.war.original spring-boot-2-7-16-actuator-micrometer-0.0.5.war

cd $current_dir

cd spring-boot-2-7-16-log4j-sync
mvn clean package
cd target && rm -f spring-boot-2-7-16-log4j-sync-0.0.5.war
mv spring-boot-2-7-16-log4j-sync-0.0.5.war.original spring-boot-2-7-16-log4j-sync-0.0.5.war