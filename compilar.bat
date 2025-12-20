@echo off
REM ============================================================
REM Script de compilación para el compilador MiniC
REM ============================================================

echo [1/7] Configurando variables de entorno...
set CLASSPATH=.;%cd%\classes;"C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"
set CUP_RUNTIME_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
set FLEX_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar"

echo.
echo [2/7] Cambiando al directorio src/compiler...
cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

echo.
echo [3/7] Compilando clases AST...
javac -d classes ast\*.java
if errorlevel 1 (
    echo ERROR: Falló la compilación de AST
    pause
    exit /b 1
)

echo.
echo [4/7] Generando Parser con CUP...
cd parser
java -jar %CUP_JAR% -parser Parser -symbols sym -nonterms parser.cup
if errorlevel 1 (
    echo ERROR: Falló la generación del parser
    pause
    exit /b 1
)
cd ..
javac -d classes parser\sym.java
if errorlevel 1 (
    echo ERROR: Falló la compilación de sym.java
    pause
    exit /b 1
)

echo.
echo [5/7] Generando Scanner con JFlex...
java -jar %FLEX_JAR% scanner\Scanner.flex
if errorlevel 1 (
    echo ERROR: Falló la generación del scanner
    pause
    exit /b 1
)

echo.
echo [6/7] Compilando Scanner y Parser...
javac -d classes -cp classes;parser;%CUP_RUNTIME_JAR% scanner\Scanner.java
if errorlevel 1 (
    echo ERROR: Falló la compilación del scanner
    pause
    exit /b 1
)
javac -d classes -cp classes;%CUP_JAR% parser\Parser.java
if errorlevel 1 (
    echo ERROR: Falló la compilación del parser
    pause
    exit /b 1
)

echo.
echo [7/7] Compilando Main...
javac -d classes -cp classes;%CUP_JAR% Main.java
if errorlevel 1 (
    echo ERROR: Falló la compilación de Main
    pause
    exit /b 1
)

echo.
echo ============================================================
echo COMPILACION EXITOSA!
echo ============================================================
echo.
echo Para ejecutar el compilador usa:
echo   java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic
echo.
echo O ejecuta: ejecutar.bat
echo ============================================================
pause
