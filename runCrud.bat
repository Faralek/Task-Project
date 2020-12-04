call gradlew clean build

if "%ERRORLEVEL%" == "0" goto rename
echo.
echo Gradlew build has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stopTomcat
echo Cannot rename file
goto fail

:stopTomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyFile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runTomcat
echo cannot copy file
goto fail

:runTomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo there were errors

:end
echo.
echo Work is finished