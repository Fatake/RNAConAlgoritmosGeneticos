import java.io.*;
import java.util.Random;
import weka.core.*;
import weka.classifiers.*;
import weka.classifiers.functions.MultilayerPerceptron;

public class RNA{
    /**
     * Almacena el nombre de la base de datos arff
     */
    private String dataBaseName;

    /**
     * Constructor
     * @param dataBaseName
     */
    public RNA(final String dataBaseName){
        this.dataBaseName = dataBaseName;
    }

    /**
     * Funcion que entrena una red neuronal
     * 
     * @param neuronasCapas
     * @param epocas
     * @param learningRate
     * @param momentum
     * @param kfolds
     * @return Float porcentaje de clasificacion
     */
    public Float entrenar(final String neuronasCapas, final int epocas, final Float learningRate, final Float momentum,
            final int kfolds) {
        String resultados = "";
        try {
            final FileReader trainreader = new FileReader(this.dataBaseName);
            final FileReader testreader = new FileReader(this.dataBaseName);

            final Instances train = new Instances(trainreader);
            final Instances test = new Instances(testreader);
            train.setClassIndex(train.numAttributes() - 1);
            test.setClassIndex(test.numAttributes() - 1);

            final MultilayerPerceptron mlp = new MultilayerPerceptron();
            // mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H
            // 4"));

            // Setting Parameters
            mlp.setHiddenLayers(neuronasCapas);// Neuronas, Capas
            mlp.setTrainingTime(epocas);// Epocas
            mlp.setLearningRate(learningRate);// LearningRate
            mlp.setMomentum(momentum);// Momentum

            // Entrenamiento
            mlp.buildClassifier(train);

            // Evalua resultados
            final Evaluation eval = new Evaluation(train);

            // Validacion cruzada
            eval.crossValidateModel(mlp, train, kfolds, new Random(1));

            trainreader.close();
            testreader.close();

            resultados = "" + eval.toSummaryString("", false);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        // regresa los resultados
        return Float.parseFloat( getPorcentaje(resultados) );
    }

    /**
     * Funcion que entrena una red neuronal
     * @param parametros en Binario
     * @return Float porcentaje de clasificacion
     */
    public Float entrenar(final PhiInstance parametros) {
        String neuronasCapas = capas(parametros.getNeuronas(), parametros.getCapas());
        int epocas = parametros.getEpocas();
        Float learningRate = parametros.getLR();
        Float momentum = parametros.getMomentum();
        int kfolds = 5;
        String resultados = "";
        try {
            final FileReader trainreader = new FileReader(this.dataBaseName);
            final FileReader testreader = new FileReader(this.dataBaseName);

            final Instances train = new Instances(trainreader);
            final Instances test = new Instances(testreader);
            train.setClassIndex(train.numAttributes() - 1);
            test.setClassIndex(test.numAttributes() - 1);

            final MultilayerPerceptron mlp = new MultilayerPerceptron();
            // mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H
            // 4"));

            // Setting Parameters
            mlp.setHiddenLayers(neuronasCapas);// Neuronas, Capas
            mlp.setTrainingTime(epocas);// Epocas
            mlp.setLearningRate(learningRate);// LearningRate
            mlp.setMomentum(momentum);// Momentum

            // Entrenamiento
            mlp.buildClassifier(train);

            // Evalua resultados
            final Evaluation eval = new Evaluation(train);

            // Validacion cruzada
            eval.crossValidateModel(mlp, train, kfolds, new Random(1));

            trainreader.close();
            testreader.close();

            resultados = "" + eval.toSummaryString("", false);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        // regresa los resultados
        return Float.parseFloat( getPorcentaje(resultados) );
    }

    /**
     * Genera un String de la siguiente forma
     * "n,n,n..."
     * Donde;
     *  n = neuronas
     * @param numeroCapas
     * @param neuronas
     * @return
     */
    public static String capas(final int numeroCapas, final int neuronas) {
        String buffer = "";
        for (int i = 1; i <= numeroCapas; i++) {
            if (i == 1) {
                buffer += neuronas;
            } else {
                buffer += "," + neuronas;
            }
        }
        return buffer;
    }

    /**
     * Analisa la salida de eval y solo retorna el
     * porcentaje de clasificacion
     * @param texto
     * @return
     */
    private String getPorcentaje(String texto){
        String aux [] = texto.split("%");
        aux = aux[0].split(" ");
        return aux[aux.length-1];
    }
    
    /***
     * Funcion Main Para pruebas de funcionamiento
     * @param args
     *
    public static void main(final String args[]) {
        final RNA aux = new RNA("ForestFire.arff");
        String buffer = "";
        buffer = aux.entrenar("2", 200, new Float(0.2), new Float(0.3), 5);

        System.out.println(buffer);

    }
    */
}
