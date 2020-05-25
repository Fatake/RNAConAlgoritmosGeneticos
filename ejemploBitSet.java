import java.util.BitSet;

/**
 * index
 */
class ejemploBitSet {
    public static void main(String args[]) {
        BitSet bits1 = new BitSet(5);
        BitSet bits2 = new BitSet(3);

        bits1.set(0,true);
        bits1.set(1,true);
        bits1.set(2,false);
        bits1.set(3,true);
        
        bits2.set(0,true);
        bits2.set(1,true);
        bits2.set(2,false);


        System.out.print("\nBinario1: ");
        System.out.println(bitSetToStr(bits1));
        System.out.print("\nBinario2: ");
        System.out.println(bitSetToStr(bits2));

        BitSet valor = new BitSet(35);
        int j = 0;
        for (int i = 0; i < 5; i++) {//Se agrega la parte de neuronas
            valor.set(i,bits1.get(i));
            j++;
        }
        System.out.println(bitSetToStr(valor));
        for (int i = 0; i < 3; i++) {
            valor.set(j,bits2.get(i));
            j++;
        }
        System.out.println(bitSetToStr(valor));

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
}