
@echo off

:: Remove Output & Class Files
del *.inorder *.preorder *.postorder >nul 2>nul
del *.class >nul 2>nul

:: Reset Build Directory
rmdir build /s /q
mkdir build

:: Print Update
echo Project cleaned.