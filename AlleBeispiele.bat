@echo off
for /F %%i in ('dir *.in /b /s') do (
  echo %%i
  java -jar main.jar %%i
)
@pause