@echo off
echo ========================================
echo PRUEBA DE COMPILACION - COMPILADOR MINIC
echo ========================================
echo.

REM Configurar variables
set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"
set CUP_RUNTIME="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
set FLEX_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

echo [1/5] Limpiando directorios...
if exist classes\compiler rmdir /s /q classes\compiler
if not exist classes mkdir classes
if not exist classes\compiler mkdir classes\compiler
if not exist classes\compiler\ast mkdir classes\compiler\ast
if not exist classes\compiler\parser mkdir classes\compiler\parser
if not exist classes\compiler\scanner mkdir classes\compiler\scanner

echo [2/5] Compilando AST...
javac -d classes ast\*.java
if errorlevel 1 (
    echo ERROR: Falló compilación de AST
    pause
    exit /b 1
)
echo OK - AST compilado

echo [3/5] Generando parser con CUP...
cd parser
java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup
if errorlevel 1 (
    echo ERROR: Falló generación del parser
    cd ..
    pause
    exit /b 1
)
cd ..
echo OK - Parser generado

echo [4/5] Compilando sym.java...
javac -d classes parser\sym.java
if errorlevel 1 (
    echo ERROR: Falló compilación de sym.java
    pause
    exit /b 1
)
echo OK - sym.java compilado

echo [5/5] Generando scanner...
java -jar %FLEX_JAR% scanner\Scanner.flex
if errorlevel 1 (
    echo ERROR: Falló generación del scanner
    pause
    exit /b 1
)
echo OK - Scanner generado

echo.
echo ========================================
echo COMPILACION INICIAL EXITOSA
echo ========================================
echo.
echo Ahora compilando scanner, parser y main...
echo.

javac -d classes -cp classes;%CUP_RUNTIME% scanner\Scanner.java
if errorlevel 1 (
    echo ERROR: Falló compilación del scanner
    pause
    exit /b 1
)
echo OK - Scanner compilado

javac -d classes -cp classes;%CUP_JAR% parser\Parser.java
if errorlevel 1 (
    echo ERROR: Falló compilación del parser
    pause
    exit /b 1
)
echo OK - Parser compilado

javac -d classes -cp classes;%CUP_JAR% Main.java
if errorlevel 1 (
    echo ERROR: Falló compilación de Main
    pause
    exit /b 1
)
echo OK - Main compilado

echo.
echo ========================================
echo TODO COMPILADO EXITOSAMENTE!
echo ========================================
echo.
echo Ejecutando compilador con demo.minic...
echo.

java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic

if errorlevel 1 (
    echo.
    echo ERROR: Falló la ejecución
    pause
    exit /b 1
)

echo.
echo ========================================
echo EXITO TOTAL!
echo ========================================
echo.
echo Archivo ast.dot generado correctamente
echo.

if exist ast.dot (
    echo Contenido de ast.dot:
    echo ----------------------------------------
    type ast.dot
    echo ----------------------------------------
)

pause
