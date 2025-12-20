@echo off
REM ============================================================
REM Script TODO EN UNO: Compila, Ejecuta y Visualiza
REM ============================================================

echo.
echo ============================================================
echo COMPILADOR MINIC - TODO EN UNO
echo ============================================================
echo.

echo *** PASO 1: COMPILACION ***
call compilar.bat
if errorlevel 1 (
    echo.
    echo Falló la compilación. Abortando...
    pause
    exit /b 1
)

echo.
echo.
echo *** PASO 2: EJECUCION ***
call ejecutar.bat
if errorlevel 1 (
    echo.
    echo Falló la ejecución. Abortando...
    pause
    exit /b 1
)

echo.
echo.
echo *** PASO 3: VISUALIZACION ***
call visualizar.bat

echo.
echo ============================================================
echo PROCESO COMPLETO FINALIZADO!
echo ============================================================
pause
