
import java.util.ArrayList;
import java.util.BitSet;

/**
 * validador
 */
public class validador {
    public validador(){};
    /**
     * Valida si el binario es una instancias validas de 
     * neuronas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param neuronas
     * @return
     */
    public boolean validaNeuronas(BitSet neuronas){
        if (neuronas.length() != PhiInstance.SIZE_NEURONA) {//Si no tiene el tamaño de las neuronas
            return false;
        }
        int j = 1,suma = 0;
        for (int i = 0; i < 5; i++) {
            if (neuronas.get(i)) {
                suma += j;
            }
            j *= 2;
        }
        if (suma > PhiInstance.MAX_NEURONA) {//si la suma binaria es mayor al maximo
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * capas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return
     */
    public boolean validaCapas(BitSet capas){
        if (capas.length() != PhiInstance.SIZE_CAPAS) {
            return false;
        }
        int j = 1,suma = 0;
        for (int i = 0; i < 5; i++) {
            if (capas.get(i)) {
                suma += j;
            }
            j *= 2;
        }
        if (suma > PhiInstance.MAX_CAPAS) {
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * epocas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return
     */
    public boolean validaEpocas(BitSet epocas){
        if (epocas.length() != PhiInstance.SIZE_EPOCAS) {
            return false;
        }
        int j = 1,suma = 0;
        for (int i = 0; i < 5; i++) {
            if (epocas.get(i)) {
                suma += j;
            }
            j *= 2;
        }
        if (suma > PhiInstance.MAX_EPOCAS) {
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * Learning rate
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return
     */
    public boolean validaLR(BitSet lr){
        if (lr.length() != PhiInstance.SIZE_LR) {
            return false;
        }
        int j = 1,suma = 0;
        for (int i = 0; i < 5; i++) {
            if (lr.get(i)) {
                suma += j;
            }
            j *= 2;
        }
        if (suma > PhiInstance.MAX_LR) {
            return false;
        }
        return true;
    }
    /**
     * Valida si el binario es una instancias validas de 
     * momentum
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return
     */
    public boolean validaMomentum(BitSet momentum){
        if (momentum.length() != PhiInstance.SIZE_MOMENTUM) {
            return false;
        }
        int j = 1,suma = 0;
        for (int i = 0; i < 5; i++) {
            if (momentum.get(i)) {
                suma += j;
            }
            j *= 2;
        }
        if (suma > PhiInstance.MAX_MOMENTUM) {
            return false;
        }
        return true;
    }
    
    public  boolean validaInstanciaCampos(PhiInstance instancia,ArrayList <PhiInstance> poblacion){
        //retorna true en caso de ser valida toda la instancia en cada valor
        //retorna false en caso de que algun campo no cumpla con su valor maximo
        if(!validaNeuronas(instancia.getNeuronasBin()))
            return false;
        if(!validaCapas(instancia.getCapasBin()))
            return false;
        if(!validaEpocas(instancia.getEpocasBin()))
            return false;
        if(!validaLR(instancia.getLRBin()))
            return false;
        if(!validaMomentum(instancia.getMomentumBin()))
            return false;
        for(PhiInstance i:poblacion)
            if(i.valor.equals(instancia.valor))
                return false;
        return true;
    }

    /*public boolean validarInstanciaRepetida(ArrayList <PhiInstance> poblacion, PhiInstance instancia ){
        if(poblacion.equals(instancia))
            return true;
        else
            return false;
        
    }*/
            
    public boolean validaInstanciaPhi(PhiInstance instancia){
        if (instancia.valor.length() != PhiInstance.SIZE_INSTANCE) {
            return false;
        }
        return true;
    }
}