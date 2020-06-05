import java.util.ArrayList;
import java.util.BitSet;

/**
 * validador
 */
public class Validador {
    private BitSetTo tranformador = new BitSetTo();
    public Validador(){};

    /**
     * Valida si el binario es una instancias validas de 
     * neuronas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param neuronas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaNeuronas(BitSet neuronas){
        if (tranformador.bitSettoLong( neuronas ) > PhiInstance.MAX_NEURONA) {
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
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaCapas(BitSet capas){
        if (tranformador.bitSettoLong( capas ) > PhiInstance.MAX_NEURONA) {
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
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaEpocas(BitSet epocas){
        if (tranformador.bitSettoLong( epocas ) > PhiInstance.MAX_NEURONA) {
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
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaLR(BitSet lr){
        if (tranformador.bitSettoLong( lr ) > PhiInstance.MAX_NEURONA) {
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
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaMomentum(BitSet momentum){
        if (tranformador.bitSettoLong( momentum ) > PhiInstance.MAX_NEURONA) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param instancia
     * PhiInstance a validar
     * @param poblacion
     * ArayList de PhiInstance
     * @return *true* en caso de ser valida toda la instancia en cada valor
     * *false* en caso de que algun campo no cumpla con su valor maximo
     */
    public boolean validaInstancia(PhiInstance instancia,ArrayList <PhiInstance> poblacion){
        if(!validaNeuronas(instanci0a.getNeuronasBin()))
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
    
    /**
     * 
     * @param instancia
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaInstanciaPhi(PhiInstance instancia){
        if (instancia.valor.length() != PhiInstance.SIZE_INSTANCE) {
            return false;
        }
        return true;
    }
}