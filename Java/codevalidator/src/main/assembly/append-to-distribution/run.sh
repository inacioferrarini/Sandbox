#!/bin/bash

export OPTS="";
for f in lib/*.jar; do export OPTS="$f:$OPTS"; done

java -cp $OPTS sandbox.inacio.codevalidator.App $@