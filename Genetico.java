import java.util.ArrayList;
import java.util.BitSet;

import javax.xml.validation.Validator;

public class Genetico {
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>();
    
    public Genetico(){

    }

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

        System.out.println("n: "+neuronas+" c: "+capas+" e: "+epocas+" rl: "+(rl/1000f)+" m:"+(mom/1000f) );

        BitSet ne = conversor.intToBitSet( neuronas,PhiInstance.SIZE_NEURONA );
        BitSet ca = conversor.intToBitSet( capas,PhiInstance.SIZE_CAPAS );
        BitSet ep = conversor.intToBitSet( epocas,PhiInstance.SIZE_EPOCAS );
        BitSet lr = conversor.intToBitSet( rl,PhiInstance.SIZE_NEURONA );
        BitSet mo = conversor.intToBitSet( mom,PhiInstance.SIZE_NEURONA );

        System.out.println("n: "+conversor.bitSetToInt(ne)+" c: "+conversor.bitSetToInt(ca)+" e: "+conversor.bitSetToInt(ep)+" rl: "+(conversor.bitSetToInt(lr)/1000f)+" m:"+(conversor.bitSetToInt(mo)/1000f) );

        System.out.println(""+RNA.capas(neuronas, capas));
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
        PhiInstance auxp = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
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
        }
        poblacion.add(new PhiInstance(hijo1));
        poblacion.add(new PhiInstance(hijo2));
    }
}