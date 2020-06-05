import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;


/**
 * Main Class para el entrenamiento don algoritmos geneticos
 */
class entrenamiento {
    // Variable que almacena la poblacio
    public static final int GENERACIONES = 20;
    public static final int TAM_POBLACION = 4;
    public static ArrayList<PhiInstance> poblacion = new ArrayList<>(TAM_POBLACION);
    /**
     * funcion Main del programa
     * @param args
     */
    public static void main(String args[]) {
        // Entrenador de la red neuronal
        RNA naturalNetwork = new RNA("ForestFire.arff");

        // Utilerias de Algoritmos geneticos
        Genetico ag = new Genetico();

        //Generar una poblacion
        poblacion = ag.generarPoblacion(TAM_POBLACION);
        
        //Evalua la poblacion Inicial 
        for (PhiInstance instancia : poblacion) {
            // fitness  = Float
            instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
        }
        odenaPoblacion();

        for (PhiInstance instancia : poblacion) {
            System.out.print(""+instancia.valorFitness+"\n");
        }

        int generacionActual = 0;
        //mientras no se han llegado al maximo de generaciones
        while (generacionActual < GENERACIONES) {
            System.out.println("\nGeneracion: "+(generacionActual+1));

            // 1) Crear una poblacion PS que seran las mejores n/2 instancias la poblacion P
            ArrayList<PhiInstance> ps = new ArrayList<PhiInstance>();
            for (int i = 0; i < (TAM_POBLACION/2); i++) {
                ps.add( poblacion.get(i) );
            }
            
            // 2) Hacer la cruza de la poblacion P un total de n/2 hijos necesitamos Y 3) colocar esos n/2 hijos en PS 
            // se generan los padres de forma aleatoria
            while (ps.size() < TAM_POBLACION) {
                int padre = (int) (Math.random()*ps.size());
                int madre = (int) (Math.random()*ps.size());
                //dentro de la funcion genera Hijo son agregados los hijos resultantes validos
                ag.generaHijo(ps,padre,madre);
            }
            
            // Mutacion
            // ahora tenemos una nueva problacion de TAM_POBLACION
            for (int i = (TAM_POBLACION/2)+1; i < TAM_POBLACION ; i++) {
                PhiInstance hijo = ps.get(i);
                hijo = ag.mutacion(hijo,ps);
                ps.set(i, hijo);
            }

            // 4) Actualizar P = Ps
            poblacion = ps;

            // 5) Reevalular los fitness
            for (PhiInstance instancia : poblacion) {
                instancia.valorFitness =  naturalNetwork.entrenar(instancia) ;
            }

            // y ordenar
            odenaPoblacion();

            for (PhiInstance instancia : poblacion) {
                System.out.print(""+instancia.valorFitness+"\n");
            }

            // Pasa a la siguiente generacion
            generacionActual ++;
            System.out.println(""+poblacion.get(0).toString());
        }
        //lista final
        odenaPoblacion();
        // retorna el mejor individuo
        System.out.println(""+poblacion.get(0).toString());
    }

    /**
     * Funcion que ordena 
     */
    public static void odenaPoblacion(){
        PhiInstance temp;
        int t = TAM_POBLACION;
        for (int i = 1; i < t; i++) {
            for (int k = t- 1; k >= i; k--) {
                if(poblacion.get(k).valorFitness > poblacion.get(k-1).valorFitness){
                    temp = poblacion.get(k);
                    poblacion.remove(k);
                    poblacion.add(k, poblacion.get(k-1)); 
                    poblacion.remove(k-1);
                    poblacion.add(k-1, temp); 
                }
            }
        }
    }
}