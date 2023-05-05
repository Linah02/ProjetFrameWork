@echo off
@REM rem compiling source code
@REM javac -classpath .\lib\servlet-api.jar;.\lib\r-w-x_file.jar -d .\bin\framework --enable-preview --release 19 "%cd%"\framework\src\*.java
@REM Compile.class reetra ao amin' ny src 
@REM Mamadika an' le FrameWork ho .jar de avy eo copiena mankany amin'ny lib test
@REM Avadika .war le test de avy eo alefa any amin' ny tomcat
@REM Commande m'lance anazy automatique amn' ny ligne de commande
javac -d FrameWork\src\ FrameWork\src\*.java  
cd FrameWork\src\
jar cvf  ..\..\fw.jar *
cd ..\..\ 
set classpath=%classpath%;"%cd%"\fw.jar
echo %classpath%
@REM mkdir test\WEB-INF\lib
copy FrameWork\src\fw.jar test\WEB-INF\lib
javac -classpath .\fw.jar -d .\test\WEB-INF\classes .\test\WEB-INF\src\model\*.java  
cd test\
jar cvf test.war *                                  
cd ..\
copy test\test.war "C:\Program Files (x86)\Apache Software Foundation\Tomcat 10.0\webapps\"
@REM demarre tomcat
@REM cd C:\Program Files (x86)\Apache Software Foundation\Tomcat 10.0\bin
@REM catalina.bat run
cd C:\Program Files (x86)\Apache Software Foundation\Tomcat 10.0\webapps
start http://localhost:8080/test
cd sprint4\