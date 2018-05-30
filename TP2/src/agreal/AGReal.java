/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new Problema();

        Integer geracoes = 300;

        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;

        Integer repeticoes = 30;


        try {
            PrintWriter writer = new PrintWriter("data.csv", "UTF-8");

            writer.println("Teste;Observacao;FO;FO_Pior;Tempo");


            // Casos de teste
            // 1 - Real1; 2 - Real2;
            ArrayList<String> nomes = new ArrayList<>(Arrays.asList("REAL1", "REAL2"));
            for (int i = 1; i <= repeticoes; i++) {
                ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
                Collections.shuffle(casos);

                for (int c = 1; c <= casos.size(); c++) {


                    AlgoritmoGenetico agReal;

                    Dados result;
                    long startTime = System.currentTimeMillis();

                    int teste = casos.get(c - 1);

                    int tamanho = 0;
                    double pCrossover = 0.0;
                    double pMutacao = 0.0;


                    switch (teste) {


                        case 1:
                            tamanho = 100;
                            pCrossover = 0.002;
                            pMutacao = 0.9;
                            break;

                        case 2:
                            tamanho = 50;
                            pCrossover = 0.01;
                            pMutacao = 0.1;
                            break;

                    }


                    agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
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


