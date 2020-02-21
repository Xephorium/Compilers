
@echo off

:: Clean Project
call clean.bat

:: Compile Project
javac Main.java Tree.java Node.java

:: Print Update
echo Project compilation complete. Running project.

:: Run Project
java Main