package ag;

public class Main {

    public static void main(String[] args) {
        String target = "To be, or not to be";
        int popmax = 150;
        float mutationRate = 0.01f;

        // Inicializa uma nova população
        Populacao population = new Populacao(target, mutationRate, popmax);


        while (!population.finished()){ // Enquanto nenhuma geração possui filhos igual a frase objetivo
            population.naturalSelection();
            //Cria a nova geração
            population.generate();
            // Calcula o fitness
            population.calcFitness();

            System.out.println(population.getBest());

        }

    }

}
