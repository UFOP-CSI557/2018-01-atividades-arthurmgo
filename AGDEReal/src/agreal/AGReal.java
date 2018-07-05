/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

/**
 *
 * @author fernando
 */
public class AGReal {

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

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
        
        ag.executar();
    }
    
}
