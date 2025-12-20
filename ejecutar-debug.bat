@echo off
echo ========================================
echo RECOMPILACION Y EJECUCION
echo ========================================
echo.

set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

echo Recompilando Main.java con debug...
javac -d classes -cp classes;%CUP_JAR% Main.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR recompilando Main
    pause
    exit /b 1
)
echo ✓ Main.java recompilado
echo.

echo ========================================
echo EJECUTANDO CON DEBUG...
echo ========================================
echo.

java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic 2>&1

echo.
echo ========================================
echo.

if exist ast.dot (
    echo ✓ EXITO! ast.dot generado
    echo.
    echo Contenido completo de ast.dot:
    echo ----------------------------------------
    type ast.dot
    echo ----------------------------------------
) else (
    echo ✗ ast.dot no fue generado
)

echo.
pause
