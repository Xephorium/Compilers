
@echo off

:: Remove Output & Class Files
del *.inorder *.preorder *.postorder >nul 2>nul
del *.class >nul 2>nul

:: Compile Project
javac Main.java Tree.java Node.java

echo Project compilation complete.