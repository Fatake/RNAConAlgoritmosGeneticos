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
        poblacion = generarPoblacion(10);

        //Evalua la poblacion Inicial 
        for (PhiInstance instancia : poblacion) {
            System.out.print(""+instancia.toString());
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            System.out.print(" fitness: "+instancia.valorFitness+"\n");
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
        PhiInstance aux = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));

        int neuronas = (int) (Math.random()*24);
        int capas = (int) (Math.random()*6);
        int epocas = (int) (Math.random()*2048);
        int rl = (int) (Math.random()*200);
        int mom = (int) (Math.random()*200);

        StringBuilder stringBuilder = new StringBuilder(Integer.toBinaryString(neuronas));
		String invertida = stringBuilder.reverse().toString();
        if (invertida.startsWith("0")) {
            stringBuilder = new StringBuilder(invertida);
            invertida = stringBuilder.delete(0,1).toString();
        }else if (invertida.equals("")) {
            invertida = "0";
        }
        aux.setNeurona(PhiInstance.strToBitSet(invertida,5));

        stringBuilder = new StringBuilder(Integer.toBinaryString(capas));
        invertida = stringBuilder.reverse().toString();
        if (invertida.startsWith("0")) {
            stringBuilder = new StringBuilder(invertida);
            invertida = stringBuilder.delete(0,1).toString();
        }else if (invertida.equals("")) {
            invertida = "0";
        }
        aux.setCapas(PhiInstance.strToBitSet(invertida,3));

        stringBuilder = new StringBuilder(Integer.toBinaryString(epocas));
        invertida = stringBuilder.reverse().toString();
        if (invertida.startsWith("0")) {
            stringBuilder = new StringBuilder(invertida);
            invertida = stringBuilder.delete(0,1).toString();
        }else if (invertida.equals("")) {
            invertida = "0";
        }
        aux.setEpocas(PhiInstance.strToBitSet(invertida,11));

        stringBuilder = new StringBuilder(Integer.toBinaryString(rl));
        invertida = stringBuilder.reverse().toString();
        if (invertida.startsWith("0")) {
            stringBuilder = new StringBuilder(invertida);
            invertida = stringBuilder.delete(0,1).toString();
        }else if (invertida.equals("")) {
            invertida = "0";
        }
        aux.setLR(PhiInstance.strToBitSet(invertida,8));

        stringBuilder = new StringBuilder(Integer.toBinaryString(mom));
        invertida = stringBuilder.reverse().toString();
        if (invertida.startsWith("0")) {
            stringBuilder = new StringBuilder(invertida);
            invertida = stringBuilder.delete(0,1).toString();
        }else if (invertida.equals("")) {
            invertida = "0";
        }
        aux.setMomentum(PhiInstance.strToBitSet(invertida,8));

        //Regresa el nuevo individuo
        return aux;
    }
}