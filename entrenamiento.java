
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;


/**
 * Main Class para el entrenamiento don algoritmos geneticos
 */
class entrenamiento {
    // Variable que almacena la poblacio
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>();
    public static final int TAM_POBLACION = 100;
    public static final int GENERACIONES = 1000;
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
            // fitness  = Float
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            System.out.print(" fitness: "+instancia.valorFitness+"\n");
        }
        Collections.sort(poblacion);

        int generacionActual = 0;
        //mientras no se han llegado al maximo de generaciones
        while (generacionActual < GENERACIONES) {
            System.out.println("Generacion Actual: "+(generacionActual+1));
            System.out.println("Ordenando la poblacion");

            // 1) Crear una poblacion PS que seran las mejores n/2 instancias la poblacion P
            ArrayList<PhiInstance> ps = new ArrayList<PhiInstance>();
            for (int i = 0; i < (TAM_POBLACION/2); i++) {
                ps.add( poblacion.get(i) );
            }
            // 2) Hacer la cruza de la poblacion P un total de n/2 hijos necesitamos Y 3) colocar esos n/2 hijos en PS 
            for (int i=0;i<(TAM_POBLACION/2);i+=2){
                //dentro de la funcion genera Hijo son agregados los hijos resultantes validos
                generaHijo(ps,i,i+1);
            }
            // Mutacion
            // ahora tenemos nuentra problacion de TAM_POBLACION
            for (int i = (TAM_POBLACION/2)+1; i < ps.size() ; i++) {
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
            Collections.sort(poblacion);

            // Pasa a la siguiente generacion
            generacionActual ++;
        }
        //lista final
        Collections.sort(poblacion);
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

        System.out.println(""+aux.toString());
        //Regresa el nuevo individuo
        return aux;
    }

    public static void generaHijo(ArrayList <PhiInstance> poblacion, int pad, int mad){ 
        PhiInstance auxp = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
        validador v=new validador();
        auxp=poblacion.get(pad);
        BitSet padre=auxp.valor;
        auxp=poblacion.get(mad);
        BitSet madre=auxp.valor;
        BitSet hijo1=new BitSet(35);
        BitSet hijo2=new BitSet(35);
        for(int i=0; i<35; i++){
            if(i%2==0){
                hijo1.set(i,padre.get(i));
                hijo2.set(i,madre.get(i));   
            }else{
                hijo1.set(i,madre.get(i));
                hijo2.set(i,padre.get(i));  
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
     * 
     */
    public static void Cruce(ArrayList <PhiInstance> poblacion, int pad, int mad){
        PhiInstance auxp = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
        validador v=new validador();
        auxp=poblacion.get(pad);
        BitSet padre=auxp.valor;
        auxp=poblacion.get(mad);
        BitSet madre=auxp.valor;
        BitSet hijo1=new BitSet(35);
        BitSet hijo2=new BitSet(35);
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
//return new PhiInstance(hijo1);
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