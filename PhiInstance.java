import java.util.ArrayList;
import java.util.BitSet;


/**
 * Clase donde se define la forma de una instancia
 * Cada instancia tiene 35 bits almacenado de litte endian
 *                                                        
 * Donde:                                                 
 * 00000|000|00000000000|00000000|00000000
 * 5bits 3bi  11bits     8bits     8bits
 * neuro  capa  epocas   lr       momentum
 * 
 */
public class PhiInstance {
    //
    // Longitud de los bits
    //
    public static final int SIZE_INSTANCE = 35;//35 bits
    public static final int SIZE_NEURONA = 5;
    public static final int SIZE_CAPAS = 3;
    public static final int SIZE_EPOCAS = 11;
    public static final int SIZE_LR = 8;
    public static final int SIZE_MOMENTUM = 8;
    //
    // Valores maximos binarios posibles
    //
    public static final int MAX_NEURONA = 23;//Valor maximo en bits
    public static final int MAX_CAPAS = 5;
    public static final int MAX_EPOCAS = 2047;
    public static final int MAX_LR = 199;
    public static final int MAX_MOMENTUM = 199;

    // Valor de la instancia
    public BitSet valor = new BitSet(35);

    //Valor Fitness
    public Float valorFitness;

    //
    private final BitSetTo convertidor = new BitSetTo();

    // Maneja litte endian
    /**
     * Recibe un bitset 
     * @param valor
     */
    public PhiInstance(BitSet valor) {
        this.valor = valor;
    }

    /**
     * Constructor que recibe un tamaño
     * de instancia
     * @param valor
     */
    public PhiInstance(int size){
        this.valor = new BitSet(size);
    }

    /**
     * Constructor para establecer una nueva instancia con 
     * neuronas, capas, epocas, learningRatem, momentum
     * @param neurona
     * @param capas
     * @param epocas
     * @param learningR
     * @param momentum
     */
    public PhiInstance(BitSet neurona, BitSet capas, BitSet epocas, BitSet learningR, BitSet momentum) {
        BitSet valor = new BitSet(35);
        int j = 0;
        for (int i = 0; i < 5; i++) {//Se agrega la parte de neuronas
            valor.set(i, neurona.get(i));
            j++;
        }
        
        for (int i = 0; i < 3; i++) {//Se agregan las capas
            valor.set(j, capas.get(i));
            j++;
        }

        for (int i = 0; i < 11; i++) {//Se agregan las epocas
            valor.set(j, epocas.get(i));
            j++;
        }
        
        for (int i = 0; i < 8; i++) {//Se agrega El learning rate
            valor.set(j, learningR.get(i));
            j++;
        }
        
        for (int i = 0; i < 8; i++) {//Se agrega el momentum
            valor.set(j, momentum.get(i));
            j ++;
        }
        
        this.valor = valor;
    }

    //
    // setters
    //
    public void setNeurona(BitSet neurona){
        for (int i = 0; i < 5; i++) {//Se actualia los bits de neurona
            valor.set(i, neurona.get(i));
        }
    }
    public void setCapas(BitSet capas){
        int j = 5;
        for (int i = 0; i < 3; i++) {
            valor.set(j, capas.get(i));
            j++;
        }
    }
    public void setEpocas(BitSet epocas){
        int j = 8;
        for (int i = 0; i < 11; i++) {
            valor.set(j, epocas.get(i));
            j++;
        }
    }
    public void setLR(BitSet lr){
        int j = 19;
        for (int i = 0; i < 8; i++) {
            valor.set(j, lr.get(i));
            j++;
        }
    }
    public void setMomentum(BitSet momentum){
        int j = 27;
        for (int i = 0; i < 8; i++) {
            valor.set(j, momentum.get(i));
            j++;
        }
    }
    
    //
    // Getters Binarios
    //
    public BitSet getNeuronasBin(){
        BitSet aux = new BitSet(5);
        for (int i = 0; i < 5; i++) {//Se actualiza los bits de neurona
            aux.set(i, valor.get(i));
        }
        return aux;
    } 
    public BitSet getCapasBin(){
        BitSet aux = new BitSet(3);
        int j = 5;
        for (int i = 0; i < 3; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getEpocasBin(){
        BitSet aux = new BitSet(11);
        int j = 8;
        for (int i = 0; i < 11; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getLRBin(){
        BitSet aux = new BitSet(8);
        int j = 19;
        for (int i = 0; i < 8; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getMomentumBin(){
        BitSet aux = new BitSet(8);
        int j = 27;
        for (int i = 0; i < 8; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    
    //
    // Getters
    //
    public int getNeuronas(){
        BitSet aux = this.getNeuronasBin();
        int nuevo = (int) convertidor.bitSetToInt(aux);

        /**
         * si
         * 0---3
         * 1---4
         * nuevo---nuevo+3
         */
        return nuevo+3;
    } 
    public int getCapas(){
        BitSet aux = this.getCapasBin();
        int valor = (int) convertidor.bitSetToInt(aux);

        /**
         * si
         * 0---1
         * 1---2
         * valor----valor+1
         */
        return valor+1;
    }
    public int getEpocas(){
        BitSet aux = this.getEpocasBin();
        int valor = (int) convertidor.bitSetToInt(aux);

        /**
         * si
         * 0---452
         * 1---453
         * 2048---2500
         */
        return valor+452;
    }
    public Float getLR(){
        BitSet aux = this.getLRBin();
        int valor = (int) convertidor.bitSetToInt(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float( ((valor+200)/1000f));
    }
    public Float getMomentum(){
        BitSet aux = this.getMomentumBin();
        int valor = (int) convertidor.bitSetToInt(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float( (valor+200)/1000f );
    }
    
    /**
     * Retorna la cadena de los valores de la Instancia
     */
    public String toString(){
        String neuronasCapas = RNA.capas(this.getNeuronas(), this.getCapas());
        int epocas = this.getEpocas();
        Float learningRate = this.getLR();
        Float momentum = this.getMomentum();
        return "n/c: "+neuronasCapas+" e: "+epocas+" lr: "+learningRate+" m: "+momentum;
    }   
}