del *.class
javac -cp ".;weka.jar" RNA.java
javac entrenamiento.java
java --add-opens=java.base/java.lang=ALL-UNNAMED -cp ".;weka.jar" entrenamiento > salida.txt
pause