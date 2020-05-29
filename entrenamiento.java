import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;

/**
 * Main Class para el entrenamiento don algoritmos geneticos
 */
class entrenamiento {
    // Variable que almacena la poblacio
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>();
    public static int TAM_POBLACION = 10;
    public static Float FITNESS_THRESHOLD = 80.0f;
    /**
     * funcion Main del programa
     * @param args
     */
    public static void main(String args[]) {
        RNA naturalNetwork = new RNA("ForestFire.arff");

        System.out.println("Generando poblacion");
        //Generar una poblacion
        poblacion = generarPoblacion(TAM_POBLACION);
        
        //Evalua la poblacion Inicial 
        System.out.println("\nEvaluando P con Fitness");
        for (PhiInstance instancia : poblacion) {
            System.out.print(""+instancia.toString());
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            System.out.print(" fitness: "+instancia.valorFitness+"\n");
        }

        //mientras max fitness(h) < FITNESS_THRESHOLD
        while (calculaMaximo() < FITNESS_THRESHOLD) {
            System.out.println("Ordenando la poblacion");
            // Ordena la poblacion de mayor a menor fitness
            Collections.sort(poblacion);

            
            // 1) Crear una poblacion PS que seran las mejores n/2 instancias la poblacion P
            ArrayList<PhiInstance> ps = new ArrayList<>();
            for (int i = 0; i < (TAM_POBLACION/2); i++) {
                ps.add(poblacion.get(i));
            }
            
            // 2) Hacer la cruza de la poblacion P un total de n/2 hijos necesitamos
            // 3) colocar esos n/2 hijos en PS y mutar uno o 2 de ellos
            // 4) Actualizar P = Ps
            // 5) Reevalular los fitness
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

        System.out.println(""+aux.toString());
        //Regresa el nuevo individuo
        return aux;
    }

    /**
     * Calcula el max Fitness(h)
     * @return
     */
    public static Float calculaMaximo(){
        Float aux = 0.0f;
        for (PhiInstance instancia : poblacion) {
            if (instancia.valorFitness > aux) {
                aux = instancia.valorFitness;
            }
        }
        return aux;
    }

}