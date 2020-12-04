call runCrud.bat

if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo There were errors in runCrud.bat - breaking work
goto fail

:openBrowser
call start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Error in opening browser url - breaking work
goto fail

:fail
echo.
echo there were errors

:end
echo.
echo Work is finished