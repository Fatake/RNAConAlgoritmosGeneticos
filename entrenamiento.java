import java.util.ArrayList;
import java.util.BitSet;

/**
 * Main Class para el entrenamiento don algoritmos geneticos
 */
class entrenamiento {
    // Variable que almacena la poblacio
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>();
    /**
     * funcion Main del programa
     * @param args
     */
    public static void main(String args[]) {
        RNA naturalNetwork = new RNA("ForestFire.arff");

        //Generar una poblacion
        poblacion = generarPoblacion(100);
        int i = 1;
        //Evalua la poblacion Inicial 
        for (PhiInstance instancia : poblacion) {
            System.out.println("Entrenando instancia"+i);
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            System.out.println("valor: "+instancia.valorFitness);
            i++;
        }
    }

    /**
     * Funcion que genera una Poblacion de N individuos 
     * se almacena en un ArrayList
     * La poblacion generada es totalmente ale
     * @param cantidadIndividuos
     * @return
     */
    public static ArrayList<PhiInstance> generarPoblacion(int cantidadIndividuos){
        ArrayList<PhiInstance> poblacionaux = new ArrayList<>();
        for (int i = 0; i < cantidadIndividuos; i++) {
            poblacionaux.add( generaIndividuo() );
        }
        return poblacionaux;
    }
    /**
     * Genera un individuo de la la clase
     * PhiInstance de forma aleatorio
     * @return
     */
    public static PhiInstance generaIndividuo(){
        //Crea un BitSet del tamaño de SIZE_INSTANCE = 35
        BitSet aux2 = new BitSet(PhiInstance.SIZE_INSTANCE);
        for (int i = 0; i < PhiInstance.SIZE_INSTANCE; i++) {
            //Genera un numero aleatorio del 0 al 1225
            int rand = (int) (Math.random()*1225+1);
            if (i % rand == 0) {// si i mod rand es 0
                aux2.set(i);//Pone el bit de esa posicion en 1
            }//Si no por defecto es 0
        }
        //Regresa el nuevo individuo
        return new PhiInstance(aux2);
    }
}