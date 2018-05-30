/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecde.ecmodel;

import ecde.metodo.ESReal;
import ecde.problema.Problema;
import ecde.problema.ProblemaDeJong;
import ecde.problema.ProblemaRastrigin;
import ecde.solucao.Individuo;

/**
 *
 * @author fernando
 */
public class ESRealMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double minimo = -100.0;
        Double maximo = 100.0;
        Integer nVariaveis = 100;
        Problema problema = new ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 20; // Tamanho da populacao
        Integer lambda = 100; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.07; // mutacao - aplicacao ao descendente - variacao/perturbacao

       ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        Individuo melhor = esReal.executar();
       
        System.out.println(melhor);
        
        
    }

}
