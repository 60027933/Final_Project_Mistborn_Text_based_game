@echo off
setlocal enabledelayedexpansion
title Mistborn Adventure Game

:: check for java
java -version >nul 2>&1

if %errorlevel% neq 0 (
    echo  Java was not found on your computer.
    echo  Java is required to run this game.
    echo.

    winget --version >nul 2>&1
    if %errorlevel% equ 0 (
        echo  Would you like to install Java automatically?
        set /p AUTO="  Enter Y to install, or N to open the download page: "
        echo.

        if /i "!AUTO!"=="Y" (
            echo  Installing Java, this may take a minute...
            echo  Please do not close this window.
            echo.
            winget install --id Microsoft.OpenJDK.21 -e --silent
            if !errorlevel! neq 0 (
                echo  Automatic install failed.
                start https://www.java.com/en/download/
                echo.
                echo  Install Java, then run this launcher again. Or just try again I guess
                pause
                exit /b 1
            )
            echo.
            echo  Java has been installed
            echo  Please close this window and run the launcher again.
            pause
            exit /b 0
        )
    )

    echo  You do not have java. Please download it
    start https://www.java.com/en/download/
    echo.
    echo  After installing Java, run this launcher again.
    pause
    exit /b 1
)

echo.
echo  Compiling game files, please wait...

if not exist "bin" mkdir bin

del "%TEMP%\mistborn_sources.txt" >nul 2>&1
for /r "src" %%f in (*.java) do (
    set "JPATH=%%f"
    set "JPATH=!JPATH:\=/!"
    echo "!JPATH!" >> "%TEMP%\mistborn_sources.txt"
)

if not exist "%TEMP%\mistborn_sources.txt" (
    echo.
    echo  ERROR: Game files not found.
    echo  Make sure the "src" folder is in the same location as this launcher.
    pause
    exit /b 1
)
for %%A in ("%TEMP%\mistborn_sources.txt") do (
    if %%~zA==0 (
        echo.
        echo  ERROR: Game files not found.
        echo  Make sure the "src" folder is in the same location as this launcher.
        del "%TEMP%\mistborn_sources.txt" >nul 2>&1
        pause
        exit /b 1
    )
)

:: ── Compilation time: record start ───────────────────────────────────────────
set "COMPILE_START=%time%"

javac -d bin @"%TEMP%\mistborn_sources.txt" 2>"%TEMP%\mistborn_errors.txt"

if %errorlevel% neq 0 (
    echo.
    echo  ERROR: Something went wrong while preparing the game.
    copy "%TEMP%\mistborn_errors.txt" "error_log.txt" >nul
    echo  A log file called error_log.txt has been saved next to the launcher.
    echo  Send that file to the developer for help.
    del "%TEMP%\mistborn_sources.txt" >nul 2>&1
    del "%TEMP%\mistborn_errors.txt" >nul 2>&1
    pause
    exit /b 1
)

del "%TEMP%\mistborn_sources.txt" >nul 2>&1
del "%TEMP%\mistborn_errors.txt" >nul 2>&1

:: ── Compilation time: calculate and display ───────────────────────────────────
set "COMPILE_END=%time%"

for /f "tokens=1-4 delims=:.," %%a in ("%COMPILE_START: =0%") do (
    set /a "s_h=%%a, s_m=1%%b-100, s_s=1%%c-100, s_cs=1%%d-100"
)
for /f "tokens=1-4 delims=:.," %%a in ("%COMPILE_END: =0%") do (
    set /a "e_h=%%a, e_m=1%%b-100, e_s=1%%c-100, e_cs=1%%d-100"
)
set /a "s_total = s_h*360000 + s_m*6000 + s_s*100 + s_cs"
set /a "e_total = e_h*360000 + e_m*6000 + e_s*100 + e_cs"
set /a "elapsed = e_total - s_total"
if %elapsed% lss 0 set /a "elapsed += 8640000"
set /a "elapsed_s  = elapsed / 100"
set /a "elapsed_cs = elapsed %% 100"
if %elapsed_cs% lss 10 set "elapsed_cs=0%elapsed_cs%"

echo  Finished compiling in %elapsed_s%.%elapsed_cs%s
echo.

:: ── Maximize the console window ───────────────────────────────────────────────
powershell -command "Add-Type -Name W -Namespace '' -MemberDefinition '[DllImport(\"user32.dll\")] public static extern bool ShowWindow(IntPtr h, int n); [DllImport(\"kernel32.dll\")] public static extern IntPtr GetConsoleWindow();'; [W]::ShowWindow([W]::GetConsoleWindow(), 3)"

timeout 1 > NUL
cls

java -cp bin src/textGame

echo.