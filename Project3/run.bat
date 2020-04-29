
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build src\*.java src\model\*.java src\model\nodes\*.java src\scanner\*.java src\parser\*.java src\semantics\*.java

:: Print Update
echo Project compiled.
echo Running project.

:: Run Project (Binaries Read from "build" Directory)
java -cp build TestSemantics %*