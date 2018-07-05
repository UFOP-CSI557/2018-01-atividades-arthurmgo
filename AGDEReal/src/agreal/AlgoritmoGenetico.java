/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal;

import java.util.Collections;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {

    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;

    // Dados do problema
    // Problema - DeJong
    Problema problema;
    // Mínimo
    Double minimo;
    // Máximo
    Double maximo;
    // Variáveis
    Integer nVariaveis;
    
    Double F = 1.0;
    Double Cr = 0.01;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Double minimo, Double maximo, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
    }

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Double executar() {

        populacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

        // Enquanto o critério de parada não for satisfeito - Gerações
        for (int g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanho; i++) {
                
                //aq
                
                 // Selecionar r0, r1, r2
                int r0, r1, r2;

                do {
                    r0 = rnd.nextInt(this.tamanho);
                } while (r0 == i);
                

                do {
                    r1 = rnd.nextInt(this.tamanho);
                } while (r1 == r0);

                do {
                    r2 = rnd.nextInt(this.tamanho);
                } while (r2 == r1 || r2 == r0);

                Individuo trial = new Individuo(minimo, maximo, this.nVariaveis);

                Individuo xr0 = (Individuo) populacao.getIndividuos().get(r0);
                Individuo xr1 = (Individuo) populacao.getIndividuos().get(r1);
                Individuo xr2 = (Individuo) populacao.getIndividuos().get(r2);

                // Gerar perturbacao - diferenca
                gerarPerturbacao(trial, xr1, xr2);
                // Mutacao - r0 + F * perturbacao
                mutacao(trial, xr0);

                // Target
                // DE/rand/1/bin
                Individuo target = (Individuo) populacao.getIndividuos().get(i);
                // Crossover
                crossover(trial, target);

                // Selecao
                problema.calcularFuncaoObjetivo(trial);

                novaPopulacao.getIndividuos().add(trial);
                
                //ate aq
                
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(minimo, maximo, nVariaveis);
                    Individuo desc2 = new Individuo(minimo, maximo, nVariaveis);

                    // Progenitores
                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);

                    // Ponto de corte
                    int corte = rnd.nextInt(p1.getVariaveis().size());

                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(p1, p2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(p2, p1, desc2, corte);

                    // Mutação
                    // Descendente 1
                    mutacaoPorVariavel(desc1);
                    // Descendente 2
                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                    
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos()
                    .subList(this.tamanho,
                            populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            //Imprimir a situacao atual
//            System.out.println("Gen = " + g +
//                    "\tCusto = "
//                    + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        }

        System.out.println("Melhor resultado: ");
        System.out.println(populacao.getIndividuos().get(0).getVariaveis());
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();

    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Double valor = (1.0 - alpha) * ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getVariaveis().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = individuo.getVariaveis().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getVariaveis().set(i, valor);

                }

            }
        }

    }
    
    
    
    private void gerarPerturbacao(Individuo trial, Individuo xr1, Individuo xr2) {

        // trial <- Diferenca entre r1 e r2
        for (int i = 0; i < this.nVariaveis; i++) {
            Double diferenca = xr1.getVariaveis().get(i)
                    - xr2.getVariaveis().get(i);

            trial.getVariaveis().add(reparaValor(diferenca));
        }

    }

    private void mutacao(Individuo trial, Individuo xr0) {

        // trial <- r0 + F * perturbacao (trial)
        for (int i = 0; i < this.nVariaveis; i++) {

            Double valor = this.F * xr0.getVariaveis().get(i)
                    + this.F * (trial.getVariaveis().get(i));

            trial.getVariaveis().set(i, reparaValor(valor));
        }

    }

    private void crossover(Individuo trial, Individuo target) {

        Random rnd = new Random();
        int j = rnd.nextInt(this.nVariaveis);

        for (int i = 0; i < this.nVariaveis; i++) {

            if (!(rnd.nextDouble() <= this.Cr || i == j)) {
                // Target
                trial.getVariaveis().set(i, target.getVariaveis().get(i));
            }

        }

    }

    private Double reparaValor(Double valor) {
        if (valor < this.minimo) {
            valor = this.minimo;
        } else if (valor > this.maximo) {
            valor = this.maximo;
        }

        return valor;
    }

}
