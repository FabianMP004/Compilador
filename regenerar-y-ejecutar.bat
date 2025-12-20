@echo off
echo ========================================
echo REGENERAR PARSER Y EJECUTAR CON DEBUG
echo ========================================
echo.

set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

echo [1] Regenerando parser con debug...
cd parser
java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup 2>&1
if errorlevel 1 (
    echo ✗ ERROR regenerando parser
    cd ..
    pause
    exit /b 1
)
cd ..
echo ✓ Parser regenerado
echo.

echo [2] Recompilando sym.java...
javac -d classes parser\sym.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando sym
    pause
    exit /b 1
)
echo ✓ sym compilado
echo.

echo [3] Recompilando Parser.java...
javac -d classes -cp classes;%CUP_JAR% parser\Parser.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando Parser
    pause
    exit /b 1
)
echo ✓ Parser compilado
echo.

echo [4] Recompilando Main.java...
javac -d classes -cp classes;%CUP_JAR% Main.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando Main
    pause
    exit /b 1
)
echo ✓ Main compilado
echo.

echo ========================================
echo EJECUTANDO CON DEBUG COMPLETO...
echo ========================================
echo.

java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic 2>&1

echo.
echo ========================================
echo.

if exist ast.dot (
    echo ✓✓✓ EXITO! ast.dot generado ✓✓✓
    echo.
    echo Contenido de ast.dot:
    echo ----------------------------------------
    type ast.dot
    echo ----------------------------------------
) else (
    echo ✗ ast.dot no fue generado
)

echo.
pause
