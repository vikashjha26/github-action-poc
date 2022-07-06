@echo off  
echo Setting JAVA_HOME  
set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_333"
echo JAVA_HOME: %JAVA_HOME%
echo setting PATH
set "PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin;%Path%"
echo PATH: %PATH%

echo Building Project
mvn clean package -DskipTests