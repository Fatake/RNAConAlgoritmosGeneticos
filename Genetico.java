import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/**
 * Clase genetico
 */
public class Genetico {
    //Factor de mutación
    public static final float FACTOR_MUTACION = 0.2f;
    
    public Genetico(){ };

    /**
     * Funcion que genera una Poblacion de N individuos 
     * se almacena en un ArrayList
     * La poblacion generada es totalmente ale
     * @param cantidadIndividuos
     * @return
     */
    public ArrayList<PhiInstance> generarPoblacion(int cantidadIndividuos){
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
    public PhiInstance generaIndividuo(){
        BitSetTo conversor = new BitSetTo();
        int neuronas = (int) (Math.random()*24);
        int capas = (int) (Math.random()*6);
        int epocas = (int) (Math.random()*2048);
        int rl = (int) (Math.random()*200);
        int mom = (int) (Math.random()*200);

        //System.out.println("n: "+(neuronas+3)+" c: "+(capas+1)+" e: "+(epocas+452)+" rl: "+((rl+200)/1000f)+" m:"+((mom+200)/1000f) );

        BitSet ne = conversor.longToBitSet( neuronas,PhiInstance.SIZE_NEURONA );
        BitSet ca = conversor.longToBitSet( capas,PhiInstance.SIZE_CAPAS );
        BitSet ep = conversor.longToBitSet( epocas,PhiInstance.SIZE_EPOCAS );
        BitSet lr = conversor.longToBitSet( rl,PhiInstance.SIZE_NEURONA );
        BitSet mo = conversor.longToBitSet( mom,PhiInstance.SIZE_NEURONA );

        //Crea un BitSet del tamaño de SIZE_INSTANCE = 35
        PhiInstance aux = new PhiInstance( ne,ca,ep,lr,mo );

        System.out.println(""+aux.toString());
        //Regresa el nuevo individuo
        return aux;
    }

    /**
     * Funcion que genera un hijo dado una instancia phi padre y madre
     * @param poblacion
     * @param pad
     * @param mad
     */
    public void generaHijo(ArrayList <PhiInstance> poblacion, int pad, int mad){ 
        Validador valida = new Validador();
        // Obtiene el bitSet del padre
        BitSet padre = poblacion.get(pad).valor;
        
        // Obtiene el bitSet del Hijo
        BitSet madre = poblacion.get(mad).valor;

        // Hijos
        BitSet hijo1 = new BitSet(PhiInstance.SIZE_INSTANCE);
        BitSet hijo2 = new BitSet(PhiInstance.SIZE_INSTANCE);

        // Se toma un bit del padre y uno de la madre
        for(int i=0; i<PhiInstance.SIZE_INSTANCE; i++){
            if(i%2 == 0){
                hijo1.set( i,padre.get(i) );
                hijo2.set( i,madre.get(i) );   
            }else{
                hijo1.set( i,madre.get(i) );
                hijo2.set( i,padre.get(i) );  
            }
        }if (valida.validaInstancia(new PhiInstance(hijo1), poblacion) && valida.validaInstancia(new PhiInstance(hijo2), poblacion) ) {
            // System.out.println(" Hijos Valido ");
        }else{
            // System.out.println(" Hijos invalido ");
            
        }
        poblacion.add(new PhiInstance(hijo1));
        poblacion.add(new PhiInstance(hijo2));
    }

    /**
     * funcion mutadora
     * @param instancia
     * @return 
     */
    public PhiInstance mutacion(PhiInstance instancia,ArrayList <PhiInstance> poblacion){
        Random r = new Random();
        if(r.nextDouble() <= FACTOR_MUTACION){
            PhiInstance aux = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
            Validador v = new Validador();
            for(int i=0; i<2; i++){
                do{
                    aux = instancia;
                    //obtiene indice random de 0 a 35
                    int indiceRandom = r.nextInt(PhiInstance.SIZE_INSTANCE);
                    //obtiene valor de bit en el indice random
                    Boolean valorBit = aux.valor.get(indiceRandom);
                    //invierte el valor del bit en el indice random
                    aux.valor.set( indiceRandom, !valorBit );
                }while(!v.validaInstancia( aux, poblacion ));
                instancia = aux;
            }
        }
        return instancia;
    }
}