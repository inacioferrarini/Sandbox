@echo off
setlocal enabledelayedexpansion
set OPTS=
for /r lib %%g in (*.jar) do set OPTS=!OPTS!:%%g

java -cp $OPTS AppClient %*