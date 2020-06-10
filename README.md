# Algoritmos Genéticos para una RNA

Archivos:
1. /Documentacion
	- DocumentacionProyectofinal
2. weka.jar :Archivo ejectuable por la maquina virtual de java, contiene todas las bibliotecas de weka
3. Validador.java: Clase que valida si la instancia PhiInstance es valida tanto en bit como en una lista.
4. RNA.java: Clase que dado unos parametros de una red neuronal, trabaja y genera un modelo. 
5. PhiInstance.java: Clase que almace de forma binaria parametros de configuracion de una RNA.
6. Genetico.java: Clase que se encarga de las utilerias de un algoritmo genetico, como mutacion, generar poblacion, generar hijos.
7. Entrenamiento.java: Clase principal que se encarga de llevar ela lgoritmo genetico para la busqueda de parametros de una RNA
8. BitSetTo.java: Clase que tiene utilidades de conversion entre BitSet a string, float, long e Int.
9. compilaEjecuta.sh/.bat : Script ejecutable que compila y ejecuta el programa

------------

## Compilar
##### Linux
El programa tiene un script ejecutable llamado **compilaEjecuta.sh** un script que funciona para linux. Se ejecuta asi:
`chmod +x compilaEjecuta.sh`
`./compilaEjecuta.sh`

##### Windows
Cambiar la extension del script **compilaEjecuta.sh** a **compilaEjecuta.bat**
Dar doble click en el ejecutable por lotes de windows.

##### Comandos
`javac -cp ".;weka.jar" RNA.java`
`javac entrenamiento.java`
`java -Xmx8224M -Xms8224M -XX:ActiveProcessorCount=255 --add-opens=java.base/java.lang=ALL-UNNAMED -cp ".;weka.jar" entrenamiento > salida.txt`

