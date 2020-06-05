del *.class
javac -cp ".;weka.jar" RNA.java
javac entrenamiento.java
:: -Xmx----M -Xms----M es la memoria ram a utilizar 
:: multiplica 1024 por x donde x es la cantida de memoria ram
:: que quieres utilizar y reemplazala en los ----m

:: Para usar todos los cpu o cores
:: -XX:ActiveProcessorCount=nn
:: giate aqui https://docs.microsoft.com/en-us/archive/blogs/santhoshonline/how-to-launch-a-process-with-cpu-affinity-set
java -Xmx8224M -Xms8224M -XX:ActiveProcessorCount=128 --add-opens=java.base/java.lang=ALL-UNNAMED -cp ".;weka.jar" entrenamiento 
pause