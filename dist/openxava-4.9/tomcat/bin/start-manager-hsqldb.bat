@echo off

rem Make sure prerequisite environment variables are set
if not "%JAVA_HOME%" == "" goto gotJavaHome
echo The JAVA_HOME environment variable is not defined
echo This environment variable is needed to run this program
goto exit
:gotJavaHome
if not exist "%JAVA_HOME%\bin\java.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\javaw.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\jdb.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\javac.exe" goto noJavaHome
goto okJavaHome
:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
echo NB: JAVA_HOME should point to a JDK not a JRE
goto exit
:okJavaHome


rem Guess TOMCAT_HOME if not defined
set CURRENT_DIR=%cd%
if not "%TOMCAT_HOME%" == "" goto gotHome
set TOMCAT_HOME=%CURRENT_DIR%
if exist "%TOMCAT_HOME%\bin\catalina.bat" goto okHome
cd ..
set TOMCAT_HOME=%cd%
cd %CURRENT_DIR%
:gotHome
if exist "%TOMCAT_HOME%\bin\catalina.bat" goto okHome
echo The TOMCAT_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome


"%JAVA_HOME%\bin\java" -classpath "%TOMCAT_HOME%\lib\hsqldb.jar" org.hsqldb.util.DatabaseManagerSwing

goto end

:exit
exit /b 1

:end
