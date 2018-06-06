import agreal.AlgoritmoGenetico;
import agreal.Dados;
import ecde.metodo.DEReal;
import ecde.metodo.ESReal;
import ecde.solucao.Individuo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        Integer geracoes = 300;
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Integer repeticoes = 30;

        agreal.Problema ag_problema = new agreal.Problema();
        ecde.problema.Problema esde_problema = new ecde.problema.ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 100; // Tamanho da populacao para esreal
        Integer lambda = 100; // numero de descendentes
        Double pMutacao = 0.07; // mutacao - aplicacao ao descendente - variacao/perturbacao

        double pCrossover = 0.002;

        int D = 100;

        int gmax = 300;

        int Np = 30;
        double F = 0.001;
        double Cr = 0.9;


        try {
            PrintWriter writer = new PrintWriter("data.csv", "UTF-8");

            writer.println("Teste;Observacao;FO;Tempo");


            // Casos de teste
            // 1 - AGREAL; 2 -ESREAL; 3 - DEREAL;

            ArrayList<String> nomes = new ArrayList<>(Arrays.asList("AGREAL", "ESREAL", "DEREAL"));
            for (int i = 1; i <= repeticoes; i++) {
                ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2, 3));
                Collections.shuffle(casos);

                for (int c = 1; c <= casos.size(); c++) {

                    AlgoritmoGenetico agReal;

                    Dados agResult;
                    long startTime = System.currentTimeMillis();

                    int teste = casos.get(c - 1);

                    int tamanho = 100;


                    agResult = null;
                    long endTime;
                    long totalTime;

                    switch (teste) {

                        case 1:

                            agReal = new agreal.AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, ag_problema, minimo, maximo, nVariaveis);
                            agResult = agReal.executar();
                            endTime = System.currentTimeMillis();
                            totalTime = endTime - startTime;
                            writer.println(nomes.get(teste - 1) + ";" + i + ";" + agResult.getMelhorResultado() + ";" + totalTime);
                            break;

                        case 2:
                            ESReal esReal = new ESReal(minimo, maximo, nVariaveis, esde_problema, mu, lambda, geracoes, pMutacao);
                            Individuo melhor = esReal.executar();
                            endTime = System.currentTimeMillis();
                            totalTime = endTime - startTime;
                            writer.println(nomes.get(teste - 1) + ";" + i + ";" + melhor.getFuncaoObjetivo() + ";" + totalTime);
                            break;
                        case 3:
                            DEReal deReal = new DEReal(minimo, maximo, esde_problema, gmax, D, Np, F, Cr);
                            Individuo resultado = deReal.executar();
                            endTime = System.currentTimeMillis();
                            totalTime = endTime - startTime;
                            writer.println(nomes.get(teste - 1) + ";" + i + ";" + resultado.getFuncaoObjetivo() + ";" + totalTime);
                            break;
                    }
                }
            }
            writer.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
