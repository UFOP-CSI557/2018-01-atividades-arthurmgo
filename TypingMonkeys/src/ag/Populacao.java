package ag;


import java.util.ArrayList;
import java.util.Random;

class Populacao {

    private float mutationRate;                 // Taxa de mutação
    private Individuo[] population;             // População atual
    private ArrayList<Individuo> matingPool;    // Array para sortear elementos
    private String target;                      // Frase objetivo
    private int generations;                    // Numero de Geracoes
    private boolean finished;                   // Alguma geraçao teve membro que atingiu a frase objetivo?
    private int perfectScore;

    Random r = new Random();

    Populacao(String p, float m, int num) {
        target = p;
        mutationRate = m;
        population = new Individuo[num];
        for (int i = 0; i < population.length; i++) {
            population[i] = new Individuo(target.length());
        }
        calcFitness();
        matingPool = new ArrayList<Individuo>();
        finished = false;
        generations = 0;

        perfectScore = 1;
    }

    // Calcula o Fitness para cada um dos elementos
    void calcFitness() {
        for (int i = 0; i < population.length; i++) {
            population[i].fitness(target);
        }
    }

    // Roleta russa para selecionar os pais
    void naturalSelection() {
        matingPool.clear();

        float maxFitness = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() > maxFitness) {
                maxFitness = population[i].getFitness();
            }
        }

        for (int i = 0; i < population.length; i++) {

            float fitness = population[i].getFitness()/maxFitness;
            int n = (int) (fitness * 100);
            for (int j = 0; j < n; j++) {
                matingPool.add(population[i]);
            }
        }
    }

    // Cria uma nova geração
    void generate() {
        for (int i = 0; i < population.length; i++) {
            int a = r.nextInt(matingPool.size());
            int b = r.nextInt(matingPool.size());
            Individuo partnerA = matingPool.get(a);
            Individuo partnerB = matingPool.get(b);
            Individuo child = partnerA.crossover(partnerB);
            child.mutate(mutationRate);
            population[i] = child;
        }
        generations++;
    }


    // Fenotipo do melhor individuo
    String getBest() {
        float worldrecord = 0.0f;
        int index = 0;
        for (int i = 0; i < population.length; i++) {
            if (population[i].getFitness() > worldrecord) {
                index = i;
                worldrecord = population[i].getFitness();
            }
        }

        if (worldrecord == perfectScore ) finished = true;
        return population[index].getPhrase();
    }

    boolean finished() {
        return finished;
    }

    int getGenerations() {
        return generations;
    }

    // Calculo da media do fitness da populaçao
    float getAverageFitness() {
        float total = 0;
        for (int i = 0; i < population.length; i++) {
            total += population[i].getFitness();
        }
        return total / (population.length);
    }

    String allPhrases() {
        String everything = "";

        int displayLimit = Math.min(population.length,50);


        for (int i = 0; i < displayLimit; i++) {
            everything += population[i].getPhrase() + "\n";
        }
        return everything;
    }
}