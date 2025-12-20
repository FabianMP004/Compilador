@echo off
REM ============================================================
REM Script para ejecutar el compilador MiniC
REM ============================================================

echo Ejecutando compilador MiniC...
echo.

set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic

if errorlevel 1 (
    echo.
    echo ERROR: El compilador falló
    pause
    exit /b 1
)

echo.
echo ============================================================
echo EJECUCION EXITOSA!
echo ============================================================
echo.
echo El archivo AST se generó en: ast.dot
echo.
echo Para visualizar el grafo, usa:
echo   dot -Tpng ast.dot -o ast.png
echo.
pause
