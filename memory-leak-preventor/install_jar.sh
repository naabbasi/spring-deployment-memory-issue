#!/usr/bin/env sh

mvn install:install-file \
   -Dfile=target/memory-leak-preventor-0.1.jar \
   -DgroupId=com.example \
   -DartifactId=memory-leak-preventor \
   -Dversion=0.1 \
   -Dpackaging=jar \
   -DgeneratePom=true