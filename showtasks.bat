call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo runcrud has errors - breaking work
goto fail

:openBrowser
start chrome
if "%ERRORLEVEL%" == "0" goto openLink
echo.
echo runcrud has errors - breaking work
goto fail

:openlink
start "" "http://localhost:8080/crud/v1/task/getTasks"

:fail
echo.
echo There were errors