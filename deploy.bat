@echo off
setlocal enabledelayedexpansion

set APP_NAME=ETU003118
set SRC_DIR=src\main\java\com\itu\hello
set WEB_DIR=src\webapp
set BUILD_DIR=build
set LIB_DIR=lib
set TOMCAT_WEBAPPS=D:\xampp\tomcat\webapps
set SERVLET_API_JAR=%LIB_DIR%\jakarta.servlet-api.5.0.0.jar

REM Suppression et recréation du dossier temporaire
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes
mkdir %BUILD_DIR%\WEB-INF\lib

REM Initialisation du CLASSPATH avec le jakarta.servlet-api.jar
set CLASSPATH=%SERVLET_API_JAR%

REM Ajouter tous les fichiers .jar présents dans lib au CLASSPATH
for /r %LIB_DIR% %%f in (*.jar) do (
    set CLASSPATH=!CLASSPATH!;%%f
)

REM Compilation des fichiers Java
echo Compilation des fichiers Java...
dir /s /b %SRC_DIR%\*.java > sources.txt
javac -cp "!CLASSPATH!" -d %BUILD_DIR%\WEB-INF\classes @sources.txt
del sources.txt

REM Copier les fichiers web (HTML, JSP, etc.)
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y

REM Copier les fichiers HTML à la racine du projet (optionnel si tu en as dans la racine)
xcopy *.html %BUILD_DIR% /Y
xcopy *.jsp %BUILD_DIR% /Y

REM Copier les .jar sauf jakarta.servlet-api.jar vers WEB-INF\lib
for %%f in (%LIB_DIR%\*.jar) do (
    echo %%~nxf | findstr /i /v "jakarta.servlet-api" >nul
    if !errorlevel! equ 0 (
        xcopy "%%f" %BUILD_DIR%\WEB-INF\lib /Y
    )
)

REM Création du fichier .war dans le dossier build
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war *
cd ..

REM Déploiement vers Tomcat
copy %BUILD_DIR%\%APP_NAME%.war %TOMCAT_WEBAPPS%

echo Déploiement terminé. Redémarrez Tomcat si nécessaire.
