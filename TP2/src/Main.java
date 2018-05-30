import agreal.AlgoritmoGenetico;
import agreal.Dados;
import agreal.Problema;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ecde.problema.*;

public class Main {

    public static void main(String[] args) {





        Integer geracoes = 300;

        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;

        Integer repeticoes = 30;

        agreal.Problema ag_problema = new agreal.Problema();
        ecde.problema.Problema esde_problema = new ecde.problema.ProblemaRastrigin(nVariaveis);



        ecde.problema.Problema problema = new ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 100; // Tamanho da populacao para esreal
        Integer lambda = 100; // numero de descendentes
        Double pMutacao = 0.07; // mutacao - aplicacao ao descendente - variacao/perturbacao


        try {
            PrintWriter writer = new PrintWriter("data.csv", "UTF-8");

            writer.println("Teste;Observacao;FO;FO_Pior;Tempo");


            // Casos de teste
            // 1 - Real1; 2 - Real2;
            ArrayList<String> nomes = new ArrayList<>(Arrays.asList("AGREAL", "ESREAL", "DEREAL"));
            for (int i = 1; i <= repeticoes; i++) {
                ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2, 3));
                Collections.shuffle(casos);

                for (int c = 1; c <= casos.size(); c++) {

                    AlgoritmoGenetico agReal;

                    Dados result;
                    long startTime = System.currentTimeMillis();

                    int teste = casos.get(c - 1);

                    int tamanho = 100;
                    double pCrossover = 0.0;


                    switch (teste) {


                        case 1:
                            pCrossover = 0.002;
                            break;

                        case 2:
                            pCrossover = 0.01;
                            break;

                    }


                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, ag_problema, minimo, maximo, nVariaveis);
                    result = agReal.executar();


                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;


                    writer.println(nomes.get(teste - 1) + ";" + i + ";" + result.getMelhorResultado() + ";" + result.getPiorResultado() + ";" + totalTime);

                }


            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
