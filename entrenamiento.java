import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;


/**
 * Main Class para el entrenamiento don algoritmos geneticos
 */
class entrenamiento {
    // Variable que almacena la poblacio
    public static final int GENERACIONES = 1000;
    public static final int TAM_POBLACION = 4;
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>(TAM_POBLACION);
    /**
     * funcion Main del programa
     * @param args
     */
    public static void main(String args[]) {
        RNA naturalNetwork = new RNA("ForestFire.arff");

        //Generar una poblacion
        poblacion = generarPoblacion(TAM_POBLACION);
        
        //Evalua la poblacion Inicial 
        for (PhiInstance instancia : poblacion) {
            // fitness  = Float
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
        }
        odenaPoblacion();

        for (PhiInstance instancia : poblacion) {
            System.out.print(""+instancia.valorFitness+"\n");
        }

        int generacionActual = 0;
        //mientras no se han llegado al maximo de generaciones
        while (generacionActual < GENERACIONES) {
            System.out.println("\nGeneracion: "+(generacionActual+1));

            // 1) Crear una poblacion PS que seran las mejores n/2 instancias la poblacion P
            ArrayList<PhiInstance> ps = new ArrayList<PhiInstance>();
            for (int i = 0; i < (TAM_POBLACION/2); i++) {
                ps.add( poblacion.get(i) );
            }
            
            // 2) Hacer la cruza de la poblacion P un total de n/2 hijos necesitamos Y 3) colocar esos n/2 hijos en PS 
            // se generan los padres de forma aleatoria
            while (ps.size() < TAM_POBLACION) {
                int padre1 = (int) (Math.random()*ps.size());
                int padre2 = (int) (Math.random()*ps.size());
                //dentro de la funcion genera Hijo son agregados los hijos resultantes validos
                generaHijo(ps,padre1,padre2);
            }
            // Mutacion
            // ahora tenemos nuentra problacion de TAM_POBLACION
            for (int i = (TAM_POBLACION/2)+1; i < TAM_POBLACION ; i++) {
                PhiInstance hijo = ps.get(i);
                hijo = PhiInstance.mutacion(hijo,ps);
                ps.set(i, hijo);
            }

            // 4) Actualizar P = Ps
            poblacion = ps;
            // 5) Reevalular los fitness
            for (PhiInstance instancia : poblacion) {
                instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            }

            // y ordenar
            odenaPoblacion();

            for (PhiInstance instancia : poblacion) {
                System.out.print(""+instancia.valorFitness+"\n");
            }

            // Pasa a la siguiente generacion
            generacionActual ++;
        }
        //lista final
        odenaPoblacion();
        // retorna el mejor individuo
        System.out.println(""+poblacion.get(0).toString());
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

    /**
     * Funcion que genera un hijo dado una instancia phi padre y madre
     * @param poblacion
     * @param pad
     * @param mad
     */
    public static void generaHijo(ArrayList <PhiInstance> poblacion, int pad, int mad){ 
        PhiInstance auxp = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
        validador v = new validador();
        auxp = poblacion.get(pad);
        BitSet padre = auxp.valor;
        auxp = poblacion.get(mad);
        BitSet madre = auxp.valor;
        BitSet hijo1 = new BitSet(35);
        BitSet hijo2 = new BitSet(35);
        for(int i=0; i<35; i++){
            if(i%2 == 0){
                hijo1.set( i,padre.get(i) );
                hijo2.set( i,madre.get(i) );   
            }else{
                hijo1.set( i,madre.get(i) );
                hijo2.set( i,padre.get(i) );  
            }
        }
        if(v.validaInstanciaCampos(new PhiInstance(hijo1),poblacion) && v.validaInstanciaCampos(new PhiInstance(hijo2),poblacion)){
            poblacion.add(new PhiInstance(hijo1));
            poblacion.add(new PhiInstance(hijo2));
        }else{
            Cruce(poblacion,pad,mad);
        }
    }
    
    /**
     * Funcion que permite hacer un cruze entre un padre y madre
     * @param poblacion
     * @param pad
     * @param mad
     */
    public static void Cruce(ArrayList <PhiInstance> poblacion, int pad, int mad){
        PhiInstance auxp = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
        validador v = new validador();
        auxp = poblacion.get(pad);
        BitSet padre = auxp.valor;
        auxp = poblacion.get(mad);
        BitSet madre = auxp.valor;
        BitSet hijo1 = new BitSet(35);
        BitSet hijo2 = new BitSet(35);
        int j;
        int i=0;
        int k=(PhiInstance.SIZE_NEURONA)/2;
        for(j=0; j<PhiInstance.SIZE_NEURONA;j++){
            if(j<k){
                hijo1.set(i,padre.get(j));
                hijo2.set(i,madre.get(j));   
            }else{
                hijo1.set(i,madre.get(j));
                hijo2.set(i,padre.get(j)); 
            }  
            i++;
        }   
        k=(PhiInstance.SIZE_CAPAS)/2;
        for(j=0; j<PhiInstance.SIZE_CAPAS;j++){
            if(j<k){
                hijo1.set(i,padre.get(j));
                hijo2.set(i,madre.get(j));   
            }else{
                hijo1.set(i,madre.get(j));
                hijo2.set(i,padre.get(j)); 
            }  
            i++;
        }
        k=(PhiInstance.SIZE_EPOCAS)/2;
        for(j=0; j<PhiInstance.SIZE_EPOCAS;j++){
            if(j<k){
                hijo1.set(i,padre.get(j));
                hijo2.set(i,madre.get(j));   
            }else{
                hijo1.set(i,madre.get(j));
                hijo2.set(i,padre.get(j)); 
            } 
            i++;
        }
        k=(PhiInstance.SIZE_LR)/2;
        for(j=0; j<PhiInstance.SIZE_LR;j++){
            if(j<k){
                hijo1.set(i,padre.get(j));
                hijo2.set(i,madre.get(j));   
            }else{
                hijo1.set(i,madre.get(j));
                hijo2.set(i,padre.get(j)); 
            }  
            i++;
        }
        k=(PhiInstance.SIZE_MOMENTUM)/2;
        for(j=0; j<PhiInstance.SIZE_MOMENTUM;j++){
            if(j<k){
                hijo1.set(i,padre.get(j));
                hijo2.set(i,madre.get(j));   
            }else{
                hijo1.set(i,madre.get(j));
                hijo2.set(i,padre.get(j)); 
            }  
            i++;
        }
        if(v.validaInstanciaCampos(new PhiInstance(hijo1),poblacion) && v.validaInstanciaCampos(new PhiInstance(hijo2),poblacion)){
            poblacion.add(new PhiInstance(hijo1));
            poblacion.add(new PhiInstance(hijo2));
        }
    }

    /**
     * Funcion que ordena 
     */
    public static void odenaPoblacion(){
        PhiInstance temp;
        int t = TAM_POBLACION;
        for (int i = 1; i < t; i++) {
            for (int k = t- 1; k >= i; k--) {
                if(poblacion.get(k).valorFitness > poblacion.get(k-1).valorFitness){
                    temp = poblacion.get(k);
                    poblacion.remove(k);
                    poblacion.add(k, poblacion.get(k-1)); 
                    poblacion.remove(k-1);
                    poblacion.add(k-1, temp); 
                }
            }
        }
    }
}