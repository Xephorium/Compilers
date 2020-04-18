
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build src\TestParser.java src\model\* src\scanner\* src\parser\*

:: Print Update
echo Project compiled.
echo Running project.

:: Run Project (Binaries Read from "build" Directory)
java -cp build TestParser %*