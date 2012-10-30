#!/bin/bash

export OPTS="lib/${project.artifactId}-${project.version}.jar"
export OPTS="lib/logback-classic-1.0.7.jar:$OPTS"
export OPTS="lib/logback-core-1.0.7.jar:$OPTS"
export OPTS="lib/slf4j-api-1.6.6.jar:$OPTS"

java -cp $OPTS ${package}.App $@