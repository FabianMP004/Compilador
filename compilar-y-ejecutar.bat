@echo off
echo ========================================
echo COMPILACION COMPLETA - TODOS LOS PASOS
echo ========================================
echo.

set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"
set CUP_RUNTIME="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
set FLEX_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

echo [PASO 1] Limpiando y creando directorios...
if exist classes rmdir /s /q classes
mkdir classes
mkdir classes\compiler
mkdir classes\compiler\ast
mkdir classes\compiler\parser
mkdir classes\compiler\scanner
echo ✓ Directorios preparados
echo.

echo [PASO 2] Compilando archivos AST...
javac -d classes ast\*.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando AST
    pause
    exit /b 1
)
echo ✓ AST compilado
echo.

echo [PASO 3] Verificando parser.cup y archivos generados...
if not exist parser\Parser.java (
    echo Generando parser...
    cd parser
    java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup 2>&1
    if errorlevel 1 (
        echo ✗ ERROR generando parser
        cd ..
        pause
        exit /b 1
    )
    cd ..
)
echo ✓ Parser.java existe
echo.

echo [PASO 4] Compilando sym.java...
javac -d classes parser\sym.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando sym.java
    pause
    exit /b 1
)
echo ✓ sym.java compilado
echo.

echo [PASO 5] Verificando Scanner.java...
if not exist scanner\Scanner.java (
    echo Generando scanner...
    java -jar %FLEX_JAR% scanner\Scanner.flex 2>&1
    if errorlevel 1 (
        echo ✗ ERROR generando scanner
        pause
        exit /b 1
    )
)
echo ✓ Scanner.java existe
echo.

echo [PASO 6] Compilando Scanner.java...
javac -d classes -cp classes;%CUP_RUNTIME% scanner\Scanner.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando Scanner
    pause
    exit /b 1
)
echo ✓ Scanner.java compilado
echo.

echo [PASO 7] Compilando Parser.java...
javac -d classes -cp classes;%CUP_JAR% parser\Parser.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando Parser
    pause
    exit /b 1
)
echo ✓ Parser.java compilado
echo.

echo [PASO 8] Compilando Main.java...
javac -d classes -cp classes;%CUP_JAR% Main.java 2>&1
if errorlevel 1 (
    echo ✗ ERROR compilando Main
    pause
    exit /b 1
)
echo ✓ Main.java compilado
echo.

echo ========================================
echo COMPILACION EXITOSA!
echo ========================================
echo.
echo [PASO 9] EJECUTANDO EL COMPILADOR...
echo.
echo Archivo de entrada: tests\demo.minic
echo.

java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic 2>&1

if errorlevel 1 (
    echo.
    echo ✗ ERROR en la ejecución
    pause
    exit /b 1
)

echo.
echo ========================================
echo EXITO TOTAL!
echo ========================================
echo.

if exist ast.dot (
    echo ✓ Archivo ast.dot generado exitosamente
    echo.
    echo Ubicación: %CD%\ast.dot
    echo.
    echo Primeras líneas del AST:
    echo ----------------------------------------
    type ast.dot | more +1
    echo ----------------------------------------
) else (
    echo ✗ ERROR: ast.dot no fue generado
)

echo.
echo Presiona cualquier tecla para finalizar...
pause > nul
