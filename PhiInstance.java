
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

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

    //Factor de mutación
    public static final float FACTOR_MUTACION = 0.2f;

    //Valor Fitness
    public Float valorFitness;

    // Maneja litte endian
    /**
     * 2a0|2a1|2a2|2a4 
     *  0   0   0   0
     * 
     * @param valor
     */
    public PhiInstance(BitSet valor) {
        this.valor = valor;
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

    /**
     * funcion mutadora
     * @param instancia
     * @return 
     */
    public static PhiInstance mutacion(PhiInstance instancia,ArrayList <PhiInstance> poblacion){
        Random r = new Random();
        if(r.nextDouble() <= FACTOR_MUTACION){
            PhiInstance aux=new PhiInstance(new BitSet(35));
            validador v=new validador();
            for(int i=0;i<2;i++){
                do{
                    aux=instancia;
                    //obtiene indice random de 0 a 35
                    int indice_random=r.nextInt(SIZE_INSTANCE);
                    //obtiene valor de bit en el indice random
                    Boolean valor_bit=aux.valor.get(indice_random);
                    //invierte el valor del bit en el indice random
                    aux.valor.set(indice_random,!valor_bit);
                }while(!v.validaInstanciaCampos(aux,poblacion));
                instancia=aux;
            }
        }
        return instancia;
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
        int nuevo = (int) (((int) bitSettoLong(aux)*27f)/23f);

        /**
         * si
         * 27 es a 23
         * ?  es a valor
         */
        return nuevo;
    } 
    public int getCapas(){
        BitSet aux = this.getCapasBin();
        int valor = (int) bitSettoLong(aux);
        int nuevo = (int) ((valor*6f)/5f);

        /**
         * si
         * 6 es a 5
         * ?  es a valor
         */
        return nuevo;
    }
    public int getEpocas(){
        BitSet aux = this.getEpocasBin();
        int valor = (int) bitSettoLong(aux);
        int nuevo = (int) ((valor*2500f)/2047f);

        /**
         * si
         * 2500 es a 2047
         *   ?  es a valor
         */
        return nuevo;
    }
    public Float getLR(){
        BitSet aux = this.getLRBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float( (((valor*400f)/199f)/1000f));
    }
    public Float getMomentum(){
        BitSet aux = this.getMomentumBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float((((valor*400f)/199f)/1000f));
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

    /**
     * Retorna en forma String 01
     * el BitSet
     * @param bits
     * @return
     */
    public static String bitSetToStr(BitSet bits){
        int size = bits.length();
        String buffer = "";
        for (int i = 0; i < size; i++) {
            if (bits.get(i)) {
                buffer += "1";
            } else {
                buffer += "0";
            }
        }
        return buffer;
    }

    /**
     * String "01001" to Bitset
     * 
     * @param bits
     * @param size
     * @return
     */
    public static BitSet strToBitSet(String bits,int size){
        BitSet aux = new BitSet(size);
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                aux.set(i);
            }else{
                aux.set(i, false);
            }
        }
        return aux;
    }

    /**
     * BitSettoLong
     * @param bits
     * @return
     */
    public long bitSettoLong(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }

    /**
     * longToBitSet
     * @param value
     * @return
     */
    public BitSet longToBitSet(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
            if (value % 2L != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }
        return bits;
    }
}