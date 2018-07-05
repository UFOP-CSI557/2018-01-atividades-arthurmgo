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
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new Problema();

        Integer tamanho = 12000;
        Double pCrossover = 1.0;
        Double pMutacao = 0.08;
        Integer geracoes = 300;

        Double minimo = -500.0;
        Double maximo = 500.0;
        Integer nVariaveis = 50;

        Integer repeticoes = 30;

        try {
            
            PrintWriter writer = new PrintWriter("data.csv", "UTF-8");
            writer.println("NúmeroDaExecução;ResultadoDaFO;TempoDeExecuçãoEmMilissegundos");

            for (int i = 1; i <= repeticoes; i++) {

                AlgoritmoGenetico agReal;

                Double result;
                long startTime = System.currentTimeMillis();

                agReal = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
                result = agReal.executar();

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                writer.println(i + ";" + result + ";" + totalTime);

            }
            
            writer.close();


        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
       

    }
}
