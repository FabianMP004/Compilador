@echo off
echo ========================================
echo PRUEBA SOLO PARSER CUP
echo ========================================
echo.

set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler\parser"

echo Directorio actual: %CD%
echo.
echo Verificando que parser.cup existe...
if not exist parser.cup (
    echo ERROR: parser.cup no encontrado!
    pause
    exit /b 1
)
echo ✓ parser.cup encontrado
echo.

echo Generando parser con CUP...
echo Comando: java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup
echo.

java -jar %CUP_JAR% -parser Parser -symbols sym parser.cup 2>&1

set ERROR_LEVEL=%ERRORLEVEL%

echo.
echo Codigo de salida: %ERROR_LEVEL%
echo.

if %ERROR_LEVEL% NEQ 0 (
    echo ========================================
    echo ERROR: Falló la generación del parser
    echo ========================================
    pause
    exit /b 1
)

echo ========================================
echo EXITO: Parser generado correctamente!
echo ========================================
echo.

echo Verificando archivos generados...
if exist Parser.java (
    echo ✓ Parser.java generado
    dir Parser.java | findstr "Parser.java"
) else (
    echo ✗ Parser.java NO se generó
)

if exist sym.java (
    echo ✓ sym.java generado
    dir sym.java | findstr "sym.java"
) else (
    echo ✗ sym.java NO se generó
)

echo.
echo ========================================
echo PROCESO COMPLETADO
echo ========================================
echo.
echo Presiona cualquier tecla para continuar...
pause > nul
