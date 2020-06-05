import java.util.BitSet;

public class BitSetTo {

    public BitSetTo(){ };

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
     * String "01001" to Bitset
     * 
     * @param bits
     * @param size
     * @return
     */
    public BitSet strToBitSet(String bits,int size){
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
     * 
     * @param value 
     * Valor a convertir
     * @param size 
     * Tamaño de bitset
     * @return Bitset(size)
     */
    public BitSet longToBitSet(long value,int size) {
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

    /**
     * 
     * @param num
     * Numero entero a convertir
     * @param size
     * Tamaño del bitset
     * @return Bitset(size)
     */
    public BitSet intToBitSet(int num,int size){
        char[] bits = Integer.toBinaryString(num).toCharArray();  
        BitSet bitSet = new BitSet(size);  
        for(int i = 0; i < bits.length; i++){  
            if(bits[i] == '1'){
                bitSet.set(i, true);
            }
            else{
                bitSet.set(i, false);
            }                
        }
        return bitSet;
    }

    /**
     * Retorna el valor entero de un Bitset
     * @param bitSet
     * @return
     */
    public int bitSetToInt(BitSet bitSet) {
        int intValue = 0;
        for (int bit = 0; bit < bitSet.length(); bit++) {
            if (bitSet.get(bit)) {
                intValue |= (1 << bit);
            }
        }
        return intValue;
    }
}