# Algoritmos Genéticos para una RNA

Archivos:
1. /Documentacion
	- DocumentacionProyectofinal
2. /Ejemplo
	- algoritmoGenetivo.java
	- ejemploBitSet.java
3. weka.jar :Archivo ejectuable por la maquina virtual de java, contiene todas las bibliotecas de weka
4. RNA.java : Clase que entrena una rna dado unos parámetros
5. PhilInstance.java :clase que almacena cada instancia de un individuo en forma binaria
6. entrenamiento.java :Clase principal que contiene el main, en ella se hace el algoritmo genetico
7. validador.java :Clase con conjunto de funciones que validan instancias PhiInstance
8. ForestFire.arff :Base de datos 
9. compilarParaRNA.bat :Archivo ejectutable por lotes de windows

------------

### RNA.java
Clase de java que gestiona el uso de las bibliotecas de **weka.jar**
Tambien tiene entre comentarios un método main para pruebas.
El ejecutable por lotes para windows **compilarParaRNA.bat** s
olo va a funcionar si no estan comentadas las lineas del main dentro de **rna.java**