
@echo off

:: Clean Project
call clean.bat

:: Compile Project
javac -d build src\Main.java src\Tree.java src\Node.java

:: Print Update
echo Project compiled.
echo Running project.

:: Run Project in "out" directory
java -cp build Main