package agreal;

/**
 *
 * @author fernando
 */
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double soma = 0.0;

        for( Double var : individuo.getVariaveis() ) {
            soma += Math.pow(var, 2);
        }
        
        individuo.setFuncaoObjetivo(soma);
        
    }
    
}
