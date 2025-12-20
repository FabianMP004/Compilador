@echo off
REM ============================================================
REM Script para visualizar el AST generado
REM ============================================================

echo Visualizando AST...
echo.

cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"

if not exist ast.dot (
    echo ERROR: No se encontró el archivo ast.dot
    echo.
    echo Primero ejecuta: ejecutar.bat
    pause
    exit /b 1
)

echo Archivo ast.dot encontrado!
echo.
echo Generando imagen PNG...
dot -Tpng ast.dot -o ast.png

if errorlevel 1 (
    echo.
    echo ERROR: Graphviz no está instalado o no está en el PATH
    echo.
    echo Para instalar Graphviz:
    echo 1. Descarga desde: https://graphviz.org/download/
    echo 2. Instala y agrega al PATH de Windows
    pause
    exit /b 1
)

echo.
echo ============================================================
echo IMAGEN GENERADA EXITOSAMENTE!
echo ============================================================
echo.
echo Archivo generado: ast.png
echo Abriendo imagen...
start ast.png

pause
