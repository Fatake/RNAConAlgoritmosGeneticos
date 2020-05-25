@echo off
ECHO Compilando...
javac -cp ".;weka.jar" RNA.java

ECHO ejecutando
java --add-opens=java.base/java.lang=ALL-UNNAMED -cp ".;weka.jar" RNA 
pause
exit