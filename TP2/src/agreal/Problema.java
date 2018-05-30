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
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double soma = 0.0;
                
        for( Double var : individuo.getVariaveis() ) {
            soma += Math.pow(var, 2) - 10*Math.cos(2*Math.PI*var);
        }
        
        individuo.setFuncaoObjetivo(10 * individuo.getVariaveis().size() + soma);
        
    }
    
}
