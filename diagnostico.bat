@echo off
echo ========================================
echo DIAGNOSTICO COMPLETO - COMPILADOR MINIC
echo ========================================
echo.

set BASE_DIR=C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador
set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"
set FLEX_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar"

echo [VERIFICANDO ESTRUCTURA DE ARCHIVOS]
echo ========================================
echo.

cd "%BASE_DIR%\src\compiler"

echo Verificando archivos AST críticos...
if exist "ast\IntLiteral.java" (echo ✓ IntLiteral.java) else (echo ✗ IntLiteral.java FALTA & set ERROR=1)
if exist "ast\FloatLiteral.java" (echo ✓ FloatLiteral.java) else (echo ✗ FloatLiteral.java FALTA & set ERROR=1)
if exist "ast\TypeNode.java" (echo ✓ TypeNode.java) else (echo ✗ TypeNode.java FALTA & set ERROR=1)
if exist "ast\Program.java" (echo ✓ Program.java) else (echo ✗ Program.java FALTA & set ERROR=1)
if exist "ast\FuncDecl.java" (echo ✓ FuncDecl.java) else (echo ✗ FuncDecl.java FALTA & set ERROR=1)
if exist "ast\VarDecl.java" (echo ✓ VarDecl.java) else (echo ✗ VarDecl.java FALTA & set ERROR=1)

echo.
echo Verificando parser y scanner...
if exist "parser\parser.cup" (echo ✓ parser.cup) else (echo ✗ parser.cup FALTA & set ERROR=1)
if exist "scanner\Scanner.flex" (echo ✓ Scanner.flex) else (echo ✗ Scanner.flex FALTA & set ERROR=1)
if exist "Main.java" (echo ✓ Main.java) else (echo ✗ Main.java FALTA & set ERROR=1)

echo.
echo Verificando archivo de prueba...
if exist "..\..\tests\demo.minic" (
    echo ✓ demo.minic encontrado
    echo.
    echo Contenido de demo.minic:
    type "..\..\tests\demo.minic"
) else (
    echo ✗ demo.minic FALTA
    set ERROR=1
)

if defined ERROR (
    echo.
    echo ERROR: Faltan archivos necesarios
    pause
    exit /b 1
)

echo.
echo [VERIFICANDO CONTENIDO DE ARCHIVOS CLAVE]
echo ========================================
echo.

echo --- IntLiteral.java (primeras 10 líneas) ---
type ast\IntLiteral.java | more /E +1 | findstr /N "^" | findstr "^[1-9]:"
echo.

echo --- TypeNode.java (primeras 10 líneas) ---
type ast\TypeNode.java | more /E +1 | findstr /N "^" | findstr "^[1-9]:"
echo.

echo --- parser.cup (líneas 55-65) ---
type parser\parser.cup | more /E +55 | findstr /N "^" | findstr "^[1-9]:"
echo.

echo [INICIANDO COMPILACION]
echo ========================================
echo.
pause

echo Creando directorio classes...
if not exist classes mkdir classes

echo.
echo [1] Compilando AST...
javac -d classes ast\*.java 2>&1
if errorlevel 1 (
    echo.
    echo ✗ ERROR en compilación de AST
    pause
    exit /b 1
)
echo ✓ AST compilado exitosamente

echo.
echo [2] Generando parser con CUP...
cd parser
java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup 2>&1
if errorlevel 1 (
    echo.
    echo ✗ ERROR en generación del parser
    cd ..
    pause
    exit /b 1
)
cd ..
echo ✓ Parser generado exitosamente

echo.
echo [3] Verificando archivos generados...
if exist "parser\Parser.java" (echo ✓ Parser.java generado) else (echo ✗ Parser.java NO generado & pause & exit /b 1)
if exist "parser\sym.java" (echo ✓ sym.java generado) else (echo ✗ sym.java NO generado & pause & exit /b 1)

echo.
echo [4] Compilando sym.java...
javac -d classes parser\sym.java 2>&1
if errorlevel 1 (
    echo.
    echo ✗ ERROR en compilación de sym.java
    pause
    exit /b 1
)
echo ✓ sym.java compilado

echo.
echo [5] Generando scanner con JFlex...
java -jar %FLEX_JAR% scanner\Scanner.flex 2>&1
if errorlevel 1 (
    echo.
    echo ✗ ERROR en generación del scanner
    pause
    exit /b 1
)
echo ✓ Scanner generado

echo.
echo [6] Compilando Scanner, Parser y Main...
set CUP_RUNTIME="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"

javac -d classes -cp classes;%CUP_RUNTIME% scanner\Scanner.java 2>&1
if errorlevel 1 (echo ✗ ERROR compilando Scanner & pause & exit /b 1)
echo ✓ Scanner compilado

javac -d classes -cp classes;%CUP_JAR% parser\Parser.java 2>&1
if errorlevel 1 (echo ✗ ERROR compilando Parser & pause & exit /b 1)
echo ✓ Parser compilado

javac -d classes -cp classes;%CUP_JAR% Main.java 2>&1
if errorlevel 1 (echo ✗ ERROR compilando Main & pause & exit /b 1)
echo ✓ Main compilado

echo.
echo ========================================
echo COMPILACION EXITOSA!
echo ========================================
echo.
echo [EJECUTANDO COMPILADOR]
echo ========================================
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
echo EJECUCION EXITOSA!
echo ========================================
echo.

if exist ast.dot (
    echo ✓ Archivo ast.dot generado
    echo.
    echo Primeras 20 líneas de ast.dot:
    echo ----------------------------------------
    type ast.dot | more /E +1 | findstr /N "^" | findstr "^[1-9]:" | findstr "^[1-9]:\|^1[0-9]:\|^20:"
    echo ----------------------------------------
    echo.
    echo Archivo completo guardado en: %CD%\ast.dot
) else (
    echo ✗ ERROR: No se generó ast.dot
    pause
    exit /b 1
)

echo.
echo ========================================
echo DIAGNOSTICO COMPLETO - TODO OK! ✓
echo ========================================
pause
