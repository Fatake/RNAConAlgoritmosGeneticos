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
    public static final int SIZE_NEURONA = 5;//longtitud de bits
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
            j++;
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
        for (int i = 0; i < 5; i++) {//Se actualia los bits de neurona
            aux.set(i, valor.get(i));
        }
        return aux;
    } 
    public BitSet getCapasBin(){
        BitSet aux = new BitSet(3);
        int j = 5;
        for (int i = j; i < j+3; i++) {
            aux.set(i, valor.get(i));
        }
        return aux;
    }
    public BitSet getEpocasBin(){
        BitSet aux = new BitSet(11);
        int j = 8;
        for (int i = j; i < j+11; i++) {
            aux.set(i, valor.get(i));
        }
        return aux;
    }
    public BitSet getLRBin(){
        BitSet aux = new BitSet(8);
        int j = 19;
        for (int i = j; i < j+8; i++) {
            aux.set(i, valor.get(i));
        }
        return aux;
    }
    public BitSet getMomentumBin(){
        BitSet aux = new BitSet(8);
        int j = 27;
        for (int i = j; i < j+8; i++) {
            aux.set(i, valor.get(i));
        }
        return aux;
    }
    
    //
    // Getters
    //
    public int getNeuronas(){
        BitSet aux = this.getNeuronasBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 27 es a 23
         * ?  es a valor
         */
        return (int) ((valor*27)/23);
    } 
    public int getCapas(){
        BitSet aux = this.getCapasBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 6 es a 5
         * ?  es a valor
         */
        return (int) ((valor*6)/5);
    }
    public int getEpocas(){
        BitSet aux = this.getEpocasBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 2500 es a 2047
         *   ?  es a valor
         */
        return (int) ((valor*2500)/2047);
    }
    public Float getLR(){
        BitSet aux = this.getLRBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float(((valor*400)/199)/100);
    }
    public Float getMomentum(){
        BitSet aux = this.getMomentumBin();
        int valor = (int) bitSettoLong(aux);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return new Float(((valor*400)/199)/100);
    }
    
    public String toString(){
        String neuronasCapas = new RNA("a.arff").capas(this.getNeuronas(), this.getCapas());
        int epocas = this.getEpocas();
        Float learningRate = this.getLR();
        Float momentum = this.getMomentum();
        int kfolds = 5;
        return "n/c: "+neuronasCapas+" e: "+epocas+" lr: "+learningRate+" m: "+momentum;
    }

    /**
     * Retorna en forma String 01
     * el BitSet
     * @param bits
     * @return
     */
    public String bitSetToStr(BitSet bits){
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