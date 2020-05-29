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

        //Evalua la poblacion Inicial 
        for (PhiInstance instancia : poblacion) {
            String neuronasCapas = naturalNetwork.capas(instancia.getNeuronas(), instancia.getCapas());
            int epocas = instancia.getEpocas();
            Float learningRate = instancia.getLR();
            Float momentum = instancia.getMomentum();
            int kfolds = 5;
            System.out.print("n/c: "+neuronasCapas+" e: "+epocas+" lr: "+learningRate+" m: "+momentum);
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            System.out.print(" valor: "+instancia.valorFitness+"\n");
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
            int rand = (int) (Math.random()*10+1);
            if (i % rand == 0) {// si i mod rand es 0
                aux2.set(i);//Pone el bit de esa posicion en 1
            }//Si no por defecto es 0
        }
        //Regresa el nuevo individuo
        return new PhiInstance(aux2);
    }
}