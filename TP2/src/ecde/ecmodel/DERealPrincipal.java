/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecde.ecmodel;

import ecde.metodo.DEReal;
import ecde.problema.Problema;
import ecde.problema.ProblemaDeJong;
import ecde.problema.ProblemaRastrigin;
import ecde.solucao.Individuo;

/**
 *
 * @author fernando
 */
public class DERealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Double minimo = -100.0;
        Double maximo = 100.0;
        
        int D = 100;
        Problema problema = new ProblemaRastrigin(D);
        
        int gmax = 300;
        int Np = 30;
        
        double F = 0.001;
        double Cr = 0.9;
        
        DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
        
        Individuo resultado = deReal.executar();
        System.out.println(resultado);
        
        
    }
    
}
